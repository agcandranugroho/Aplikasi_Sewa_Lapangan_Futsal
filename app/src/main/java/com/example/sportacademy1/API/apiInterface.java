package com.example.sportacademy1.API;

import com.example.sportacademy1.model.bayar.Bayar;
import com.example.sportacademy1.model.lapangan.Lapangan;
import com.example.sportacademy1.model.login.Login;
import com.example.sportacademy1.model.pembayaran.Pembayaran;
import com.example.sportacademy1.model.pemesanan.Pemesanan;
import com.example.sportacademy1.model.register.Register;
import com.example.sportacademy1.model.users.Users;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface apiInterface {
    @FormUrlEncoded
    @POST("users/login.php")
    Call<Login>loginResponse(
        @Field("id_user") String id,
        @Field("username") String username,
        @Field("password") String password
    );

    @FormUrlEncoded
    @POST("users/register.php")
    Call<Register>registerResponse(
            @Field("username") String username,
            @Field("nama") String nama,
            @Field("email") String email,
            @Field("no_telp")String no_telp,
            @Field("password") String password

    );

    @FormUrlEncoded
    @POST("users/update.php")
    Call<Users>updateUserResponse(
            @Field("id_user") String id,
            @Field("nama") String nama,
            @Field("username") String username,
            //@Field("password") String password,
            //@Field("email") String email,
            @Field("no_telp")String no_telp
    );

    @FormUrlEncoded
    @POST("users/read.php")
    Call<Users>readUserResponse(
            @Field("id_user") String id
    );

    @FormUrlEncoded
    @POST("pemesanan/insert.php")
    Call<Pemesanan>pemesananResponse(
            @Field("id_pelanggan")String id_pelanggan,
            @Field("id_lapangan")String id_lapangan,
            @Field("tanggal_main")String tanggal_main,
            @Field("durasi_start")String durasi_start,
            @Field("durasi_end")String durasi_end,
            @Field("harga")String harga
    );

    @GET("pemesanan/read.php")
    Call<Pemesanan>pemesananRead();

    @FormUrlEncoded
    @POST("lapangan/read.php")
    Call<Lapangan>jadwalmain(
            @Field("key") String keyword
    );

    @FormUrlEncoded
    @POST("pembayaran/cari.php")
    Call<Bayar>prosesPembayaran(
            @Field("key") String keyword
    );

    @FormUrlEncoded
    @POST("pembayaran/readBelumBayar.php")
    Call<Pembayaran>pembayaranRead(
            @Field("id_pelanggan") String id_pelanggan
    );

    @FormUrlEncoded
    @POST("pembayaran/update.php")
    Call<Bayar>updateBayar(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("pembayaran/readSudahBayar.php")
    Call<Pembayaran>PembayaranRead1(
            @Field("id_pelanggan") String id_pelanggan
    );

    @FormUrlEncoded
    @POST("pembayaran/delete.php")
    Call<Pembayaran>pembayaranDelete(
      @Field("id_pemesanan") String id
    );
}
