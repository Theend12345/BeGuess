package com.vyapp.beguess.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vyapp.beguess.App
import com.vyapp.beguess.R
import com.vyapp.beguess.databinding.FragmentUserBinding
import com.vyapp.beguess.databinding.FragmentUsersListBinding
import com.vyapp.beguess.databinding.ItemUserBinding
import com.vyapp.beguess.domain.model.UserDomain
import javax.inject.Inject


class UsersListFragment : Fragment() {

    @Inject
    lateinit var beguessViewModelFactory: BeguessViewModelFactory

    private val binding: FragmentUsersListBinding by lazy {
        FragmentUsersListBinding.inflate(layoutInflater)
    }

    private val viewModel: BeGuessViewModel by lazy {
        ViewModelProvider(requireActivity(), beguessViewModelFactory)[BeGuessViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as App).appComponent.userListInject(this@UsersListFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.getUsers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.layoutManager = LinearLayoutManager(requireContext())

        viewModel.usersLiveData.observe(viewLifecycleOwner){
            binding.rv.adapter = ItemAdapter(it)
        }
    }

    private inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val username: TextView = itemView.findViewById(R.id.username_tv)
        val score: TextView = itemView.findViewById(R.id.score_tv)
        val place: TextView = itemView.findViewById(R.id.place_tv)

        fun bind(item: UserDomain) {
            username.text = item.username.uppercase()
            score.text = item.score.toString()
            place.text = item.index.toString()
        }
    }

    private inner class ItemAdapter(val items: List<UserDomain>) :
        RecyclerView.Adapter<UserViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
            val view = layoutInflater.inflate(R.layout.item_user, parent, false)
            return UserViewHolder(view)
        }

        override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
            val item = items[position]
            holder.bind(item)
        }

        override fun getItemCount() = items.size

    }


}