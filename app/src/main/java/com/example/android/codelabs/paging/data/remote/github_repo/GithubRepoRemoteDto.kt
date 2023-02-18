package com.example.android.codelabs.paging.data.remote.github_repo

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

/**
 * Data class to hold repo responses from searchRepo API calls.
 */

@JsonClass(generateAdapter = true)
data class GithubRepoRemoteDto(
    @SerializedName("total_count") val total: Int = 0,
    @SerializedName("items") val items: List<Repository> = emptyList(),
    val nextPage: Int? = null
) {
    /**
     * Immutable model class for a Github repo that holds all the information about a repository.
     * Objects of this type are received from the Github API, therefore all the fields are annotated
     * with the serialized name.
     * This class also defines the Room repos table, where the repo [id] is the primary key.
     */
    @JsonClass(generateAdapter = true)
    data class Repository(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String,
        @SerializedName("full_name") val fullName: String? ="",
        @SerializedName("description") val description: String?,
        @SerializedName("html_url") val url: String,
        @SerializedName("stargazers_count") val stars: Int? =0,
        @SerializedName("forks_count") val forks: Int,
        @SerializedName("language") val language: String?
    )
}