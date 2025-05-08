package org.firstinspires.ftc.teamcode.kotlinKit.auxiliary.matchData

import com.qualcomm.robotcore.util.ReadWriteFile
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.firstinspires.ftc.robotcore.internal.system.AppUtil

class MatchData() : MutableList<Enum<*>> by mutableListOf() {

    private val file = AppUtil.getInstance().getSettingsFile("./kotlinKit/matchDataCache.json")
    val hasCache: Boolean get() = file.exists()

    fun load(data: MatchData) {
        this.clear()
        this += data
    }

    val wrapper: MatchDataWrapper get() = this.wrap()
    constructor(wrapper: MatchDataWrapper) : this() { this.load(wrapper.wrappedData) }
    constructor(vararg data: Enum<*>) : this() { this.load(data.toList() as MatchData) }

    fun wrap(): MatchDataWrapper {
        val wrapper = this.map { it.name to it.ordinal }
        return wrapper as MatchDataWrapper
    }

    fun cacheToFile() {
        val json = Json.encodeToString(this.wrapper)
        ReadWriteFile.writeFile(file, json)
    }

    fun loadFromFile() {
        if (!file.exists()) return

        val json = ReadWriteFile.readFile(file)
        val wrapper = Json.decodeFromString<MatchDataWrapper>(json)

        this.load(wrapper.wrappedData)
    }
}