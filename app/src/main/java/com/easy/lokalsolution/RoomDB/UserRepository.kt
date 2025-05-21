package com.easy.lokalsolution.RoomDB

import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    val allUsers: Flow<List<UserDB>> = userDao.getAllUsers()

    suspend fun insert(user: UserDB) = userDao.insertUser(user)
    suspend fun delete(user: UserDB) = userDao.deleteUser(user)
}
