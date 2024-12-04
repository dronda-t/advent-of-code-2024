import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.readString


fun readInput(name: String): List<String> {
    return readInputRaw(name).split('\n').dropLast(1)
}

fun readInputRaw(name: String): String {
    return SystemFileSystem.source(Path("inputs/$name.txt")).buffered().readString()
}