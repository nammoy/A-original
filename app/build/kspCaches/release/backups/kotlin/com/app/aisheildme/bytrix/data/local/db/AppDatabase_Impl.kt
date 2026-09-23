package com.app.aisheildme.bytrix.`data`.local.db

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class AppDatabase_Impl : AppDatabase() {
  private val _checkInHistoryDao: Lazy<CheckInHistoryDao> = lazy {
    CheckInHistoryDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(1,
        "cdfa65aaf747ea020504dadfe28c88bc", "d5da3c726aede09d8abdd883959b29ba") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `checkin_ai_summaries` (`id` TEXT NOT NULL, `created_at` INTEGER NOT NULL, `summary_text` TEXT NOT NULL, `patient_name` TEXT, `clinic_name` TEXT, `appointment_type` TEXT, `reason_for_visit` TEXT, `device_label` TEXT, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'cdfa65aaf747ea020504dadfe28c88bc')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `checkin_ai_summaries`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection):
          RoomOpenDelegate.ValidationResult {
        val _columnsCheckinAiSummaries: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsCheckinAiSummaries.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCheckinAiSummaries.put("created_at", TableInfo.Column("created_at", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCheckinAiSummaries.put("summary_text", TableInfo.Column("summary_text", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCheckinAiSummaries.put("patient_name", TableInfo.Column("patient_name", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCheckinAiSummaries.put("clinic_name", TableInfo.Column("clinic_name", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCheckinAiSummaries.put("appointment_type", TableInfo.Column("appointment_type",
            "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCheckinAiSummaries.put("reason_for_visit", TableInfo.Column("reason_for_visit",
            "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCheckinAiSummaries.put("device_label", TableInfo.Column("device_label", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysCheckinAiSummaries: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesCheckinAiSummaries: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoCheckinAiSummaries: TableInfo = TableInfo("checkin_ai_summaries",
            _columnsCheckinAiSummaries, _foreignKeysCheckinAiSummaries, _indicesCheckinAiSummaries)
        val _existingCheckinAiSummaries: TableInfo = read(connection, "checkin_ai_summaries")
        if (!_infoCheckinAiSummaries.equals(_existingCheckinAiSummaries)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |checkin_ai_summaries(com.app.aisheildme.bytrix.data.local.db.CheckInSummaryEntity).
              | Expected:
              |""".trimMargin() + _infoCheckinAiSummaries + """
              |
              | Found:
              |""".trimMargin() + _existingCheckinAiSummaries)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "checkin_ai_summaries")
  }

  public override fun clearAllTables() {
    super.performClear(false, "checkin_ai_summaries")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(CheckInHistoryDao::class, CheckInHistoryDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override
      fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>):
      List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun checkInHistoryDao(): CheckInHistoryDao = _checkInHistoryDao.value
}
