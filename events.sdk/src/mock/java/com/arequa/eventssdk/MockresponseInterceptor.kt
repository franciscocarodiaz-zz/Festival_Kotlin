package com.arequa.eventssdk

import okhttp3.*

/**
 * Created by FCD on 29/05/2018.
 */
class MockresponseInterceptor(val responses: HashMap<String, String>,
                              val defaultJsonResponse: String): Interceptor {

    val MEDIA_TYPE_JSON = MediaType.parse("application/json")

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = getResponseFor(request.url().toString())

        return Response.Builder()
                .body(ResponseBody.create(MEDIA_TYPE_JSON, response))
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message("success")
                .build()
    }

    private fun getResponseFor(url: String): String {
        return if (responses.containsKey(url))
            responses[url]!!
        else
            defaultJsonResponse
    }
}