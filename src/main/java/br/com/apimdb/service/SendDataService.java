package br.com.apimdb.service;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public abstract class SendDataService<E> implements Callback<E> {

	private Retrofit retrofit;
	protected TaskComplete handler;
	protected Interceptor requestInterceptor;

	public SendDataService(String urlBase) {
		this(urlBase, null, null, null);
	}

	public SendDataService(String urlBase, TaskComplete handler) {
		this(urlBase, handler, null, null);
	}

	public SendDataService(String urlBase, 
			TaskComplete handler, 
			HostnameVerifier hostnameVerifier,
			Interceptor requestInterceptor) {
		this.handler = handler;

		OkHttpClient.Builder client = new OkHttpClient.Builder();
		HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
		logging.setLevel(HttpLoggingInterceptor.Level.BODY);
		client.addInterceptor(logging);

		client.connectTimeout(2, TimeUnit.MINUTES);
		client.readTimeout(2, TimeUnit.MINUTES);
		client.writeTimeout(2, TimeUnit.MINUTES);
		if (requestInterceptor != null) {
			this.requestInterceptor = requestInterceptor;
			client.addInterceptor(requestInterceptor);
		}
		client.retryOnConnectionFailure(true);
		
		if(hostnameVerifier!=null)
			client.hostnameVerifier(hostnameVerifier);

		retrofit = new Retrofit.Builder()
						.baseUrl(urlBase)
						.client(client.build())
						.addConverterFactory(JacksonConverterFactory.create())
						.build();
	}

	protected <T> T createApi(Class<T> clazz) {
		return retrofit.create(clazz);
	}

	protected abstract void setApi();

	@Override
	public void onFailure(Call<E> call, Throwable t) {
		if (handler != null) {
			handler.onFinish(null);
		} else {
			handler.onFinish(t);
		}
	}

	@Override
	public void onResponse(Call<E> call, Response<E> response) {
		if (handler != null) {
			handler.onFinish(response.body());
		}
	}
}
