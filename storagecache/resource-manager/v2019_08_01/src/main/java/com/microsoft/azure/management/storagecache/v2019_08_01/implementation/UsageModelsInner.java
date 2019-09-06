/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.storagecache.v2019_08_01.implementation;

import retrofit2.Retrofit;
import com.google.common.reflect.TypeToken;
import com.microsoft.azure.AzureServiceFuture;
import com.microsoft.azure.CloudException;
import com.microsoft.azure.ListOperationCallback;
import com.microsoft.azure.Page;
import com.microsoft.azure.PagedList;
import com.microsoft.rest.ServiceFuture;
import com.microsoft.rest.ServiceResponse;
import java.io.IOException;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import retrofit2.Response;
import rx.functions.Func1;
import rx.Observable;

/**
 * An instance of this class provides access to all the operations defined
 * in UsageModels.
 */
public class UsageModelsInner {
    /** The Retrofit service to perform REST calls. */
    private UsageModelsService service;
    /** The service client containing this operation class. */
    private StorageCacheMgmtClientImpl client;

    /**
     * Initializes an instance of UsageModelsInner.
     *
     * @param retrofit the Retrofit instance built from a Retrofit Builder.
     * @param client the instance of the service client containing this operation class.
     */
    public UsageModelsInner(Retrofit retrofit, StorageCacheMgmtClientImpl client) {
        this.service = retrofit.create(UsageModelsService.class);
        this.client = client;
    }

    /**
     * The interface defining all the services for UsageModels to be
     * used by Retrofit to perform actually REST calls.
     */
    interface UsageModelsService {
        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.azure.management.storagecache.v2019_08_01.UsageModels list" })
        @GET("subscriptions/{subscriptionId}/providers/Microsoft.StorageCache/usageModels")
        Observable<Response<ResponseBody>> list(@Path("subscriptionId") String subscriptionId, @Query("api-version") String apiVersion, @Header("accept-language") String acceptLanguage, @Header("User-Agent") String userAgent);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.azure.management.storagecache.v2019_08_01.UsageModels listNext" })
        @GET
        Observable<Response<ResponseBody>> listNext(@Url String nextUrl, @Header("accept-language") String acceptLanguage, @Header("User-Agent") String userAgent);

    }

    /**
     * Get the list of cache Usage Models available to this subscription.
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws CloudException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the PagedList&lt;UsageModelInner&gt; object if successful.
     */
    public PagedList<UsageModelInner> list() {
        ServiceResponse<Page<UsageModelInner>> response = listSinglePageAsync().toBlocking().single();
        return new PagedList<UsageModelInner>(response.body()) {
            @Override
            public Page<UsageModelInner> nextPage(String nextPageLink) {
                return listNextSinglePageAsync(nextPageLink).toBlocking().single().body();
            }
        };
    }

    /**
     * Get the list of cache Usage Models available to this subscription.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<List<UsageModelInner>> listAsync(final ListOperationCallback<UsageModelInner> serviceCallback) {
        return AzureServiceFuture.fromPageResponse(
            listSinglePageAsync(),
            new Func1<String, Observable<ServiceResponse<Page<UsageModelInner>>>>() {
                @Override
                public Observable<ServiceResponse<Page<UsageModelInner>>> call(String nextPageLink) {
                    return listNextSinglePageAsync(nextPageLink);
                }
            },
            serviceCallback);
    }

    /**
     * Get the list of cache Usage Models available to this subscription.
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the PagedList&lt;UsageModelInner&gt; object
     */
    public Observable<Page<UsageModelInner>> listAsync() {
        return listWithServiceResponseAsync()
            .map(new Func1<ServiceResponse<Page<UsageModelInner>>, Page<UsageModelInner>>() {
                @Override
                public Page<UsageModelInner> call(ServiceResponse<Page<UsageModelInner>> response) {
                    return response.body();
                }
            });
    }

    /**
     * Get the list of cache Usage Models available to this subscription.
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the PagedList&lt;UsageModelInner&gt; object
     */
    public Observable<ServiceResponse<Page<UsageModelInner>>> listWithServiceResponseAsync() {
        return listSinglePageAsync()
            .concatMap(new Func1<ServiceResponse<Page<UsageModelInner>>, Observable<ServiceResponse<Page<UsageModelInner>>>>() {
                @Override
                public Observable<ServiceResponse<Page<UsageModelInner>>> call(ServiceResponse<Page<UsageModelInner>> page) {
                    String nextPageLink = page.body().nextPageLink();
                    if (nextPageLink == null) {
                        return Observable.just(page);
                    }
                    return Observable.just(page).concatWith(listNextWithServiceResponseAsync(nextPageLink));
                }
            });
    }

    /**
     * Get the list of cache Usage Models available to this subscription.
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the PagedList&lt;UsageModelInner&gt; object wrapped in {@link ServiceResponse} if successful.
     */
    public Observable<ServiceResponse<Page<UsageModelInner>>> listSinglePageAsync() {
        if (this.client.subscriptionId() == null) {
            throw new IllegalArgumentException("Parameter this.client.subscriptionId() is required and cannot be null.");
        }
        if (this.client.apiVersion() == null) {
            throw new IllegalArgumentException("Parameter this.client.apiVersion() is required and cannot be null.");
        }
        return service.list(this.client.subscriptionId(), this.client.apiVersion(), this.client.acceptLanguage(), this.client.userAgent())
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Page<UsageModelInner>>>>() {
                @Override
                public Observable<ServiceResponse<Page<UsageModelInner>>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<PageImpl<UsageModelInner>> result = listDelegate(response);
                        return Observable.just(new ServiceResponse<Page<UsageModelInner>>(result.body(), result.response()));
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<PageImpl<UsageModelInner>> listDelegate(Response<ResponseBody> response) throws CloudException, IOException, IllegalArgumentException {
        return this.client.restClient().responseBuilderFactory().<PageImpl<UsageModelInner>, CloudException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<PageImpl<UsageModelInner>>() { }.getType())
                .registerError(CloudException.class)
                .build(response);
    }

    /**
     * Get the list of cache Usage Models available to this subscription.
     *
     * @param nextPageLink The NextLink from the previous successful call to List operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws CloudException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the PagedList&lt;UsageModelInner&gt; object if successful.
     */
    public PagedList<UsageModelInner> listNext(final String nextPageLink) {
        ServiceResponse<Page<UsageModelInner>> response = listNextSinglePageAsync(nextPageLink).toBlocking().single();
        return new PagedList<UsageModelInner>(response.body()) {
            @Override
            public Page<UsageModelInner> nextPage(String nextPageLink) {
                return listNextSinglePageAsync(nextPageLink).toBlocking().single().body();
            }
        };
    }

    /**
     * Get the list of cache Usage Models available to this subscription.
     *
     * @param nextPageLink The NextLink from the previous successful call to List operation.
     * @param serviceFuture the ServiceFuture object tracking the Retrofit calls
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<List<UsageModelInner>> listNextAsync(final String nextPageLink, final ServiceFuture<List<UsageModelInner>> serviceFuture, final ListOperationCallback<UsageModelInner> serviceCallback) {
        return AzureServiceFuture.fromPageResponse(
            listNextSinglePageAsync(nextPageLink),
            new Func1<String, Observable<ServiceResponse<Page<UsageModelInner>>>>() {
                @Override
                public Observable<ServiceResponse<Page<UsageModelInner>>> call(String nextPageLink) {
                    return listNextSinglePageAsync(nextPageLink);
                }
            },
            serviceCallback);
    }

    /**
     * Get the list of cache Usage Models available to this subscription.
     *
     * @param nextPageLink The NextLink from the previous successful call to List operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the PagedList&lt;UsageModelInner&gt; object
     */
    public Observable<Page<UsageModelInner>> listNextAsync(final String nextPageLink) {
        return listNextWithServiceResponseAsync(nextPageLink)
            .map(new Func1<ServiceResponse<Page<UsageModelInner>>, Page<UsageModelInner>>() {
                @Override
                public Page<UsageModelInner> call(ServiceResponse<Page<UsageModelInner>> response) {
                    return response.body();
                }
            });
    }

    /**
     * Get the list of cache Usage Models available to this subscription.
     *
     * @param nextPageLink The NextLink from the previous successful call to List operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the PagedList&lt;UsageModelInner&gt; object
     */
    public Observable<ServiceResponse<Page<UsageModelInner>>> listNextWithServiceResponseAsync(final String nextPageLink) {
        return listNextSinglePageAsync(nextPageLink)
            .concatMap(new Func1<ServiceResponse<Page<UsageModelInner>>, Observable<ServiceResponse<Page<UsageModelInner>>>>() {
                @Override
                public Observable<ServiceResponse<Page<UsageModelInner>>> call(ServiceResponse<Page<UsageModelInner>> page) {
                    String nextPageLink = page.body().nextPageLink();
                    if (nextPageLink == null) {
                        return Observable.just(page);
                    }
                    return Observable.just(page).concatWith(listNextWithServiceResponseAsync(nextPageLink));
                }
            });
    }

    /**
     * Get the list of cache Usage Models available to this subscription.
     *
    ServiceResponse<PageImpl<UsageModelInner>> * @param nextPageLink The NextLink from the previous successful call to List operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the PagedList&lt;UsageModelInner&gt; object wrapped in {@link ServiceResponse} if successful.
     */
    public Observable<ServiceResponse<Page<UsageModelInner>>> listNextSinglePageAsync(final String nextPageLink) {
        if (nextPageLink == null) {
            throw new IllegalArgumentException("Parameter nextPageLink is required and cannot be null.");
        }
        String nextUrl = String.format("%s", nextPageLink);
        return service.listNext(nextUrl, this.client.acceptLanguage(), this.client.userAgent())
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Page<UsageModelInner>>>>() {
                @Override
                public Observable<ServiceResponse<Page<UsageModelInner>>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<PageImpl<UsageModelInner>> result = listNextDelegate(response);
                        return Observable.just(new ServiceResponse<Page<UsageModelInner>>(result.body(), result.response()));
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<PageImpl<UsageModelInner>> listNextDelegate(Response<ResponseBody> response) throws CloudException, IOException, IllegalArgumentException {
        return this.client.restClient().responseBuilderFactory().<PageImpl<UsageModelInner>, CloudException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<PageImpl<UsageModelInner>>() { }.getType())
                .registerError(CloudException.class)
                .build(response);
    }

}
