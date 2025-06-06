package lucas.momo.newsly.communs

import lucas.momo.newsly.mocks.UTC_FORMATED_MOCK
import lucas.momo.newsly.mocks.UTC_MOCK
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.Instant
import java.util.Locale
import java.util.TimeZone

class InstantExtensionsTest {
    private lateinit var locale: Locale
    private lateinit var timeZone: TimeZone

    @Before
    fun setUp() {
        locale = Locale.getDefault()
        timeZone = TimeZone.getDefault()

        Locale.setDefault(Locale.US)
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    }

    @After
    fun tearDown() {
        Locale.setDefault(locale)
        TimeZone.setDefault(timeZone)
    }

    @Test
    fun `should format instant to MMddyyyy at HHmm`() {
        val instant = Instant.parse(UTC_MOCK)

        val formatted = instant.toDateTimeFormat()

        assertEquals(UTC_FORMATED_MOCK, formatted)
    }
}
