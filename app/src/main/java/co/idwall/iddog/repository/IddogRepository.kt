package co.idwall.iddog.repository

import co.idwall.iddog.data.IddogApi
import co.idwall.iddog.domain.LoginBody
import co.idwall.iddog.domain.LoginResponse
import retrofit2.Response
import javax.inject.Inject

interface IddogRepository {
    suspend fun login(email: String): Response<LoginResponse>
}

class IddogRepositoryImpl @Inject constructor(
    private val iddogApi: IddogApi
) : IddogRepository {

    override suspend fun login(email: String) =
        iddogApi.login(LoginBody(email))

}