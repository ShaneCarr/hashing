import org.example.consistenthashing.File
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
val pageSize = 250

// need to create a ring with
// several hash functions.
class consistentHashingTest {

    val files = ArrayList<File>()
    @Before
    fun setUp() {

        // this really isn't sparse
        // i can skip ahead by a random number or something to make ti sarse, but start with this.
        for (i in 0L..10000L){
         files.add(File(i, "Name{$i}"))
        }



    }

    @After
    fun tearDown() {
    }

    @Test
    fun getBucketForLong() {
    }

    @Test
    fun getBuckets() {
    }
}
