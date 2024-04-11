package com.example.mylistingapp

import ListItem

interface ItemClickListner {

    fun onClick(data : ListItem, pos : Int)
}