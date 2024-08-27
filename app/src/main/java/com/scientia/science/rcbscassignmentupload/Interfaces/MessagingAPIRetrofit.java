package com.scientia.science.rcbscassignmentupload.Interfaces;

import com.scientia.science.rcbscassignmentupload.Datasets.NoticifationDataFinal;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MessagingAPIRetrofit {
    @Headers({"Content-Type: application/json","Authorization: key=AAAAgGFRaBc:APA91bHgvbKid_2MSupPrqpFP0T0tU7ImQWEkq2tW9g6guOwZ5x7F1tTVmNSpx2daD1AoIkvVhk0nKU8bFS9sNp83akCTh7bssoRSGvDA8g-M5W_mSp4i73-OtZoMxS7ZyoWT3A0vCA2"})
    @POST("fcm/send")
    Call<NoticifationDataFinal> getFcmMessage(@Body NoticifationDataFinal fcm);
}
