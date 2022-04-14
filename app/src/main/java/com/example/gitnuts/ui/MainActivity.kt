package com.example.gitnuts.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gitnuts.R
import com.example.gitnuts.ui.userlist.view.UserListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, UserListFragment.newInstance())
            .commit()
    }


    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}