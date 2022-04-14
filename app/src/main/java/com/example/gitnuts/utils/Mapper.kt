package com.example.gitnuts.utils

abstract class Mapper<I, O> {
    abstract fun map(input: I): O
}