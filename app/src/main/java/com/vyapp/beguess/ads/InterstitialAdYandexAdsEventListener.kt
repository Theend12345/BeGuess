package com.vyapp.beguess.ads

import android.util.Log
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData
import com.yandex.mobile.ads.interstitial.InterstitialAd
import com.yandex.mobile.ads.interstitial.InterstitialAdEventListener

class InterstitialAdYandexAdsEventListener(val interstitialAd: InterstitialAd?) :
    InterstitialAdEventListener {

    override fun onAdLoaded() {
        interstitialAd?.show()
    }

    override fun onAdFailedToLoad(adRequestError: AdRequestError) {
        Log.d("gmida", adRequestError.description)
    }

    override fun onImpression(impressionData: ImpressionData?) {

    }

    override fun onAdShown() {

    }

    override fun onAdDismissed() {


    }

    override fun onAdClicked() {

    }

    override fun onLeftApplication() {

    }

    override fun onReturnedToApplication() {


    }
}