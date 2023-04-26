package com.vyapp.beguess.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vyapp.beguess.domain.*
import com.vyapp.beguess.domain.model.TaskDomain
import com.vyapp.beguess.domain.model.UserDomain
import com.vyapp.beguess.domain.model.UserRegisterDomain
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BeGuessViewModel(
    val registrationUseCase: RegistrationUseCase,
    val sightInUseCase: SightInUseCase,
    val usersUseCase: GetAllUsersUseCase,
    val getTaskUseCase: GetTaskUseCase,
    val rightAnswerUseCase: RightAnswerUseCase,
    val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    private var _taskLiveData: MutableLiveData<TaskDomain> = MutableLiveData()
    var taskLiveData: LiveData<TaskDomain> = _taskLiveData

    private var _userLiveData: MutableLiveData<UserDomain?> = MutableLiveData()
    var userLiveData: LiveData<UserDomain?> = _userLiveData

    private var _usersLiveData: MutableLiveData<List<UserDomain>> = MutableLiveData()
    var usersLiveData: LiveData<List<UserDomain>> = _usersLiveData


    private var _isRgisterable: MutableLiveData<Boolean> = MutableLiveData()
    var isRgistrable: LiveData<Boolean> = _isRgisterable

    private var _isSiginable: MutableLiveData<Boolean> = MutableLiveData()
    var isSiginable: LiveData<Boolean> = _isSiginable

    private var _progress: MutableLiveData<Boolean> = MutableLiveData()
    var progress: LiveData<Boolean> = _progress



    fun clearUser() {
        _userLiveData.value = null
    }

    fun rightAnswer(user: UserDomain){
        disposable.add(
            rightAnswerUseCase.execute(user).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _userLiveData.value = it
                }, {
                    Log.e("ERROR_TAG", it.message.toString())
                })
        )
    }

    fun getTask() {
        disposable.add(
            getTaskUseCase.execute().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _progress.value = false
                    _taskLiveData.value = it
                },{

                })
        )
    }

    fun getUsers() {
        disposable.add(
            usersUseCase.execute().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    it.sortedByDescending { item -> item.score }
                }
                .subscribe({
                    _progress.value = false
                    _usersLiveData.value = placeList(it)
                }, {
                    Log.e("ERROR_TAG", it.message.toString())
                })
        )
    }

    private fun placeList(it: List<UserDomain>): List<UserDomain> {

        it.forEachIndexed { index, userDomain ->
            userDomain.index = (index + 1).toLong()
        }
        return it
    }

    fun registration(userRegisterDomain: UserRegisterDomain) {
        disposable.add(
            registrationUseCase.execute(userRegisterDomain).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _isRgisterable.value = true
                    _userLiveData.value = it
                }, {
                    _isRgisterable.value = false
                    Log.e("ERROR_TAG", it.message.toString())
                })
        )
    }

    fun sightIn(userRegisterDomain: UserRegisterDomain) {
        disposable.add(
            sightInUseCase.execute(userRegisterDomain).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _isSiginable.value = true
                    _userLiveData.value = it
                }, {
                    _isSiginable.value = false
                    Log.e("ERROR_TAG", it.message.toString())
                })
        )
    }

    fun setProgressTrue(){
        _progress.value = true
    }

    init {
        getUsers()
        getTask()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}