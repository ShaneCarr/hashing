import org.example.consistenthashing.File
import org.junit.After
import org.junit.Before
import org.junit.Test

val pageSize = 250

// need to create a ring with
// several hash functions.
// i reallly don't need consistent hashing
// because partitions aren't being added or removed after we "start" but for learning:)
// hash function integer https://gist.github.com/badboy/6267743
// goal https://www.toptal.com/big-data/consistent-hashing
class consistentHashingTest {

    val files = ArrayList<File>()
    @Before
    fun setUp() {

        // this really isn't sparse
        // i can skip ahead by a random number or something to make ti sarse, but start with this.
        for (i in 0L..10000L){
         files.add(File(i, "Name{$i}"))
        }

        // select top 250
        var batchNumber = 1
        var batchsize = 750
        var batches = ArrayList<List<File>>()
        var batch: List<File> = files.take(batchsize)
        batches.add(batch)

        while(batch.any()){
            batch = files.subList(batchNumber * batchsize, files.count() -1).take(batchsize)

            if(batch.any()){
                batches.add(batch)
            }

            batchNumber++
        }

        // now for these ids let's asssign them to 1/3 parrtitions
        // An integer hash function accepts an integer hash key, and returns an integer hash result with uniform distribution. In this

        // calcualte a hash /modulus 3, then add. let's see what happens when we run subsequent runs and delete items.
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
