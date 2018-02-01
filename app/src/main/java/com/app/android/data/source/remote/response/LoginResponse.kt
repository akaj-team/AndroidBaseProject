package com.app.android.data.source.remote.response

import com.google.gson.annotations.SerializedName

/**
 *
 * @author at-vinhhuynh
 */
class LoginResponse(val login: String, val id: Int,
                    @SerializedName("avatar_url") val avatarUrl: String,
                    @SerializedName("gravatar_id") val gravatarId: String, val url: String,
                    @SerializedName("html_url") val htmlUrl: String,
                    @SerializedName("followers_url") val followersUrl: String,
                    @SerializedName("following_url") val followingUrl: String,
                    @SerializedName("gists_url") val gistsUrl: String,
                    @SerializedName("starred_url") val starredUrl: String,
                    @SerializedName("subscriptions_url") val subscriptionsUrl: String,
                    @SerializedName("organizations_url") val organizationsUrl: String,
                    @SerializedName("repos_url") val reposUrl: String,
                    @SerializedName("events_url") val eventsUrl: String,
                    @SerializedName("received_events_url") val receivedEventsUrl: String,
                    @SerializedName("type") val type: String,
                    @SerializedName("site_admin") val siteAdmin: String,
                    @SerializedName("name") val name: String,
                    @SerializedName("company") val company: String,
                    @SerializedName("blog") val blog: String,
                    @SerializedName("location") val location: String,
                    @SerializedName("email") val email: String,
                    @SerializedName("hireable") val hireable: String,
                    @SerializedName("bio") val bio: String,
                    @SerializedName("public_repos") val publicRepos: String,
                    @SerializedName("followers") val followers: Int,
                    @SerializedName("following") val following: Int,
                    @SerializedName("created_at") val createdAt: String,
                    @SerializedName("updated_at") val updatedAt: String,
                    @SerializedName("private_gists") val privateGists: Int,
                    @SerializedName("total_private_repos") val totalPrivateRepos: Int,
                    @SerializedName("owned_private_repos") val ownedPrivateRepos: Int,
                    @SerializedName("disk_usage") val diskUsage: Int,
                    @SerializedName("collaborators") val collaborators: Int,
                    @SerializedName("two_factor_authentication") val twoFactorAuthentication: Boolean)
