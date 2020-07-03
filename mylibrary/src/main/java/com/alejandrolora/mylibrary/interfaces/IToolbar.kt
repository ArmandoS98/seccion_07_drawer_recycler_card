package com.alejandrolora.mylibrary.interfaces

import androidx.appcompat.widget.Toolbar

interface IToolbar {
    fun enableHomeDisplay(value: Boolean)
    fun toolbarToLoad(toolbar: Toolbar?)
}