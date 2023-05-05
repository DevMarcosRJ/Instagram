package co.tiagoaguiar.course.instagram.common.model

import android.net.Uri
import java.io.File
import java.util.*

object Database {

    val usersAuth = mutableListOf<UserAuth>()
    val posts = hashMapOf<String, MutableSet<Post>>()
    val feeds = hashMapOf<String, MutableSet<Post>>()
    val followers = hashMapOf<String, Set<String>>()
    val photo = "/storage/emulated/0/Android/media/co.tiagoaguiar.course.instagram/Instagram/2023-05-05-12-26-12-492.jpg"

    var sessionAuth: UserAuth? = null

    init {
        val userA = UserAuth(UUID.randomUUID().toString(),"UserA","userA@gmail.com", "12345678",  Uri.fromFile(
            File(photo)))

        val userB = UserAuth(UUID.randomUUID().toString(),"UserB","userB@gmail.com", "87654321",  Uri.fromFile(
        File(photo)))

        usersAuth.add(userA)
        usersAuth.add(userB)

        followers[userA.uuid] = hashSetOf()
        posts[userA.uuid] = hashSetOf()
        feeds[userA.uuid] = hashSetOf()

        followers[userB.uuid] = hashSetOf()
        posts[userB.uuid] = hashSetOf()
        feeds[userB.uuid] = hashSetOf()

        feeds[userA.uuid]?.addAll(
            arrayListOf(
                Post(UUID.randomUUID().toString(), Uri.fromFile(
                    File(photo)),
                    "desc",
                    System.currentTimeMillis(), userA),

                Post(UUID.randomUUID().toString(), Uri.fromFile(
                    File(photo)),
                    "desc",
                    System.currentTimeMillis(), userA),

                Post(UUID.randomUUID().toString(), Uri.fromFile(
                    File(photo)),
                    "desc",
                    System.currentTimeMillis(), userA),

                Post(UUID.randomUUID().toString(), Uri.fromFile(
                    File(photo)),
                    "desc",
                    System.currentTimeMillis(), userA),
            )
        )

        feeds[userA.uuid]?.toList()?.let {
            feeds[userB.uuid]?.addAll(it)
        }

        sessionAuth = usersAuth.first()
    }
}