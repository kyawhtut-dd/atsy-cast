package com.kyawhut.atsycast.msys.ui.genres

import android.content.Context
import com.kyawhut.atsycast.msys.data.network.MsysAPI
import com.kyawhut.atsycast.msys.data.network.response.MoviesResponse
import com.kyawhut.atsycast.share.network.utils.NetworkResponse
import com.kyawhut.atsycast.share.network.utils.execute
import com.kyawhut.atsycast.share.utils.Crashlytics
import com.kyawhut.atsycast.share.utils.ShareUtils.isAdult
import com.kyawhut.atsycast.share.utils.extension.Extension.isAdultOpen
import javax.inject.Inject

/**
 * @author kyawhtut
 * @date 9/10/21
 */
internal class MoviesRepositoryImpl @Inject constructor(
    private val api: MsysAPI,
    private val crashlytics: Crashlytics,
) : MoviesRepository {

    override suspend fun getMovies(
        context: Context,
        genresID: Int,
        apiKey: String,
        page: Int,
        callback: (NetworkResponse<List<MoviesResponse>>) -> Unit
    ) {
        NetworkResponse.loading(callback)
        val response = execute(crashlytics) { api.getMovies(genresID, apiKey, page) }
        if (response.isSuccess) {
            NetworkResponse.success(response.data?.filter {
                !it.moviesGenres.any { it.genresTitle.isAdult } || context.isAdultOpen
            } ?: listOf(), callback)
        } else response.post(callback)
    }
}
