package com.vyapp.beguess.ads

import android.content.Context
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.MobileAds
import com.yandex.mobile.ads.interstitial.InterstitialAd

object AdsController {

    private var instanceInt: Boolean = false

    fun loadInterstitialAd(interstitialAd: InterstitialAd?) {
        interstitialAd?.loadAd(AdRequest.Builder().build())
    }

    fun configureInterstitialAd(interstitialAd: InterstitialAd?) {
        if (!instanceInt) {
            val interstitialAdEventListener = InterstitialAdYandexAdsEventListener(interstitialAd)
            interstitialAd?.apply {
                setAdUnitId("R-M-2299938-1")
                setInterstitialAdEventListener(interstitialAdEventListener)
            }
            instanceInt = true
        }
    }

    fun initialize(context: Context) {
        MobileAds.initialize(
            context
        ) {}
    }

}