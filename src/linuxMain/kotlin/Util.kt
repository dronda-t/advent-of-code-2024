import kotlinx.io.buffered
import kotlinx.io.files.FileSystem
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.readLine

fun readInput(name: String): List<String> {
    return SystemFileSystem.source(Path("inputs/$name.txt")).buffered().use { fileSource ->
        buildList {
            while (true) {
                val line = fileSource.readLine() ?: break
                add(line)
            }
        }
    }
}

fun readInputRaw(name: String): String {
    return SystemFileSystem.source(Path("inputs/$name.txt")).buffered().use { fileSource ->
        buildString {
            while (!fileSource.exhausted()) {
                val line = fileSource.readLine() ?: break
                append(line)
            }
        }
    }
}