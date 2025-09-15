package com.maze.database
import com.google.android.gms.common.util.CollectionUtils.listOf

class ReportViolationData {

    fun loadReportViolationData(): List<ViolationData>{
        return listOf<ViolationData>(
            ViolationData("35 Balmuto ST, ON M4Y 0A3.","10/27 9:19PM"),
            ViolationData("56 Glendale AVE, ON M6R 2S8.","10/28 7:02PM"),
            ViolationData("8 Irwin AVE, ON M4Y 1K9.","10/29 4:39PM"),
            ViolationData("3000 Dufferin ST, ON M6K 3E5.","10/30 9:23PM"),
            ViolationData("373 Front ST E, ON M5A 1G4.","10/26 5:39PM"),
            ViolationData("68 Cameron AVE, ON M6M 1R2.","10/27 12:31PM")
        )
    }
}
data class ViolationData(
    val address:String,
    val time:String
)