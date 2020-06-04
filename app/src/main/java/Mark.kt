data class Mark(val name: String = "", val subject: String = "", val mark: Double = 0.0) {

    override fun toString() = name + "\t" + subject + "\t" + mark
}