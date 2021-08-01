package com.bektursun.cryptolist.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.bektursun.data.currencyTicker.CurrencyTicker
import com.bektursun.storage.roomDB.CryptoCurrencyDB
import com.bektursun.storage.roomDB.dao.CryptoCurrencyDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CryptoCurrencyDaoTest {

    private lateinit var database: CryptoCurrencyDB
    private lateinit var dao: CryptoCurrencyDao

    private val currencyOne = CurrencyTicker(
        "BTC", "BTC", "BTC", "Bitcoin", "",
        "active", 231.21
    )
    private val currencyTwo = CurrencyTicker(
        "ETH", "ETH", "ETH", "Ethereum", "",
        "", 231433.21
    )

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDB() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, CryptoCurrencyDB::class.java).build()
        dao = database.getCurrencyDao()

        dao.insertCurrencyTicker(listOf(currencyOne, currencyTwo))
    }

    @After
    fun closeDB() {
        database.close()
    }

    @Test
    fun testGetCurrencyTickers() = runBlocking {
        val currencyList: List<CurrencyTicker> = dao.fetchCurrencyTicker().first()
        assertThat(currencyList.size, equalTo(2))

        assertThat(currencyList[0], equalTo(currencyOne))
        assertThat(currencyList[1], equalTo(currencyTwo))
    }

    @Test
    fun testClearAllData() = runBlocking {
        dao.deleteCurrencies()
        assertThat(dao.fetchCurrencyTicker().first().size, equalTo(0))
    }

    @Test
    fun clearAndInsertTest() = runBlocking {
        val currencies = listOf(
            CurrencyTicker(
                "XPR", "XPR", "XPR", "XPR", "",
                "active", 11111.21
            ),
            CurrencyTicker(
                "XRP", "XRP", "XRP", "XRP", "",
                "active", 3283721.21
            )
        )
        // check insert
        dao.insertCurrencyTicker(currencies)
        assertThat(dao.fetchCurrencyTicker().first().size, equalTo(4))

        // check size after removing all data
        dao.deleteCurrencies()
        assertThat(dao.fetchCurrencyTicker().first().size, equalTo(0))
    }
}