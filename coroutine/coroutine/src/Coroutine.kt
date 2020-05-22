import kotlinx.coroutines.*

fun main() {
    suspendExample()

}

fun suspendExample() {
    GlobalScope.launch{
        suspendTest1()
    }
    GlobalScope.launch {
        suspendTest2()
    }
    Thread.sleep(20000L)
}

suspend fun suspendTest1() {
    println(1)
    yield()
    println(2)
    yield()
    println(5)
    yield()
    println(6)
    yield()
}

suspend fun suspendTest2() {
    println(3)
    yield()
    println(4)
    yield()
    println(7)
    yield()
    println(8)
    yield()
}

fun coroutineContextTest() {
    runBlocking {
        launch {
            println("first launch : ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Unconfined) {
            println("second launch : ${Thread.currentThread().name}")
        }
        launch(newSingleThreadContext("CustomThread")) {
            println("third launch : ${Thread.currentThread().name}")
        }
    }
}

fun asyncTest1() {
    runBlocking {
        val res1 = async {
            println("1")
            1
        }
        println("first async")
        val res2 = async {
            println("2")
            2
        }
        println("second async")
        val res3 = async {
            println("3")
            3
        }
        println("third async")
        println("1+2+3=${res1.await() + res2.await() + res3.await()} ")
    }
    println("finish runBlocking")
}

fun launchTest1() {
    runBlocking {
        launch {
            println(1)
            yield()
            println(3)
            yield()
            println(5)
            yield()
        }
        println("first launch")
        launch {
            println(2)
            delay(1000L)
            println(4)
            delay(1000L)
            println(6)
            delay(1000L)
        }
        println("second launch")
    }
    println("runblocking")
}