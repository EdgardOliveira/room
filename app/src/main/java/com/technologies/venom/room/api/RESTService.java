package com.technologies.venom.room.api;

import com.technologies.venom.room.models.Endereco;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RESTService {

    //consultar CEP no webservice do ViaCEP
    @GET("{cep}/json/")
    Call<Endereco> consultarCEP(@Path("cep") String cep);
}
