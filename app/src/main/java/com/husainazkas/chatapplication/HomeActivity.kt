package com.husainazkas.chatapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        checkUserLoginAccount()
    }

    private fun checkUserLoginAccount() {
        if (FirebaseAuth.getInstance().uid.isNullOrEmpty()) {
            MainActivity.launchIntentClearTask(this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_signOut ->{
                signOutUser()
            }
            R.id.nav_message ->{
                messageToFriend()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun messageToFriend() {
        FriendListActivity.launchIntent(this)
    }

    private fun signOutUser() {
        FirebaseAuth.getInstance().signOut()
        MainActivity.launchIntentClearTask(this)
        Toast.makeText(this, "You've been signed out", Toast.LENGTH_LONG).show()
    }

    /*
    override fun onBackPressed() {
        val alert_exit = AlertDialog.Builder(this)
        alert_exit.setTitle("Keluar")
        alert_exit.setPositiveButton("Ya", { dialogInterface: DialogInterface, i: Int -> finish() })
        alert_exit.setNegativeButton("Tidak", { dialogInterface: DialogInterface, i: Int -> })
        alert_exit.show()
    }
     */

    companion object {

        fun launchIntent(context: Context) {
            val intent = Intent(context, HomeActivity::class.java)
            context.startActivity(intent)
        }

        fun launchIntentClearTask(context: Context) {
            val intent = Intent(context, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK.or(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(intent)
        }

    }
}
