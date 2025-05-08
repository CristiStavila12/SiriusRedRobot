package org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.matchData

import kotlinx.serialization.Serializable

@Serializable
class MatchDataWrapper() : MutableList<Pair<String, Int>> by mutableListOf() {

    private fun load(wrapper: MatchDataWrapper) {
        this.clear()
        this += wrapper
    }

    val wrappedData: MatchData get() = this.unwrap()
    constructor(data: MatchData) : this() { this.load(data.wrapper) }

    fun unwrap(): MatchData {
        val data =  this.map { (name, ordinal) -> Class.forName(name).enumConstants[ordinal] }
        return data as MatchData
    }
}