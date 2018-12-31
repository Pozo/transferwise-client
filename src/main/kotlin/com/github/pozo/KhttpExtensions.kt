package com.github.pozo

import khttp.responses.Response

fun Response.onSuccess(function: (Response) -> Unit): Response {
    if (this.statusCode in 200..300) {
        function(this)
    }
    return this
}

fun Response.onResponse(success: (Response) -> Unit, failure: (Response) -> Unit): Response {
    when (this.statusCode) {
        200 -> success(this)
        else -> {
            failure(this)
        }
    }
    return this
}

fun Response.onOtherThanSuccess(function: (Response) -> Unit): Response {
    if (this.statusCode < 200 && this.statusCode >= 300) {
        function(this)
    }
    return this
}

fun Response.onRedirection(function: (Response) -> () -> Unit): Response {
    if (this.statusCode in 300..400) {
        function(this)
    }
    return this
}

fun Response.onClientError(function: (Response) -> () -> Unit): Response {
    if (this.statusCode in 400..500) {
        function(this)
    }
    return this
}

fun Response.onInternalServerError(function: (Response) -> () -> Unit): Response {
    if (this.statusCode >= 500) {
        function(this)
    }
    return this
}

