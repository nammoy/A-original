package com.app.aisheildme.bytrix.`data`.local.db

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class CheckInHistoryDao_Impl(
  __db: RoomDatabase,
) : CheckInHistoryDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfCheckInSummaryEntity: EntityInsertAdapter<CheckInSummaryEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfCheckInSummaryEntity = object :
        EntityInsertAdapter<CheckInSummaryEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `checkin_ai_summaries` (`id`,`created_at`,`summary_text`,`patient_name`,`clinic_name`,`appointment_type`,`reason_for_visit`,`device_label`) VALUES (?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: CheckInSummaryEntity) {
        statement.bindText(1, entity.id)
        statement.bindLong(2, entity.created_at)
        statement.bindText(3, entity.summary_text)
        val _tmpPatient_name: String? = entity.patient_name
        if (_tmpPatient_name == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpPatient_name)
        }
        val _tmpClinic_name: String? = entity.clinic_name
        if (_tmpClinic_name == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpClinic_name)
        }
        val _tmpAppointment_type: String? = entity.appointment_type
        if (_tmpAppointment_type == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpAppointment_type)
        }
        val _tmpReason_for_visit: String? = entity.reason_for_visit
        if (_tmpReason_for_visit == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpReason_for_visit)
        }
        val _tmpDevice_label: String? = entity.device_label
        if (_tmpDevice_label == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpDevice_label)
        }
      }
    }
  }

  public override suspend fun save(entity: CheckInSummaryEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfCheckInSummaryEntity.insert(_connection, entity)
  }

  public override fun observeAll(): Flow<List<CheckInSummaryEntity>> {
    val _sql: String = "SELECT * FROM checkin_ai_summaries ORDER BY created_at DESC"
    return createFlow(__db, false, arrayOf("checkin_ai_summaries")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfSummaryText: Int = getColumnIndexOrThrow(_stmt, "summary_text")
        val _columnIndexOfPatientName: Int = getColumnIndexOrThrow(_stmt, "patient_name")
        val _columnIndexOfClinicName: Int = getColumnIndexOrThrow(_stmt, "clinic_name")
        val _columnIndexOfAppointmentType: Int = getColumnIndexOrThrow(_stmt, "appointment_type")
        val _columnIndexOfReasonForVisit: Int = getColumnIndexOrThrow(_stmt, "reason_for_visit")
        val _columnIndexOfDeviceLabel: Int = getColumnIndexOrThrow(_stmt, "device_label")
        val _result: MutableList<CheckInSummaryEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CheckInSummaryEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpCreated_at: Long
          _tmpCreated_at = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpSummary_text: String
          _tmpSummary_text = _stmt.getText(_columnIndexOfSummaryText)
          val _tmpPatient_name: String?
          if (_stmt.isNull(_columnIndexOfPatientName)) {
            _tmpPatient_name = null
          } else {
            _tmpPatient_name = _stmt.getText(_columnIndexOfPatientName)
          }
          val _tmpClinic_name: String?
          if (_stmt.isNull(_columnIndexOfClinicName)) {
            _tmpClinic_name = null
          } else {
            _tmpClinic_name = _stmt.getText(_columnIndexOfClinicName)
          }
          val _tmpAppointment_type: String?
          if (_stmt.isNull(_columnIndexOfAppointmentType)) {
            _tmpAppointment_type = null
          } else {
            _tmpAppointment_type = _stmt.getText(_columnIndexOfAppointmentType)
          }
          val _tmpReason_for_visit: String?
          if (_stmt.isNull(_columnIndexOfReasonForVisit)) {
            _tmpReason_for_visit = null
          } else {
            _tmpReason_for_visit = _stmt.getText(_columnIndexOfReasonForVisit)
          }
          val _tmpDevice_label: String?
          if (_stmt.isNull(_columnIndexOfDeviceLabel)) {
            _tmpDevice_label = null
          } else {
            _tmpDevice_label = _stmt.getText(_columnIndexOfDeviceLabel)
          }
          _item =
              CheckInSummaryEntity(_tmpId,_tmpCreated_at,_tmpSummary_text,_tmpPatient_name,_tmpClinic_name,_tmpAppointment_type,_tmpReason_for_visit,_tmpDevice_label)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun all(): List<CheckInSummaryEntity> {
    val _sql: String = "SELECT * FROM checkin_ai_summaries ORDER BY created_at DESC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfSummaryText: Int = getColumnIndexOrThrow(_stmt, "summary_text")
        val _columnIndexOfPatientName: Int = getColumnIndexOrThrow(_stmt, "patient_name")
        val _columnIndexOfClinicName: Int = getColumnIndexOrThrow(_stmt, "clinic_name")
        val _columnIndexOfAppointmentType: Int = getColumnIndexOrThrow(_stmt, "appointment_type")
        val _columnIndexOfReasonForVisit: Int = getColumnIndexOrThrow(_stmt, "reason_for_visit")
        val _columnIndexOfDeviceLabel: Int = getColumnIndexOrThrow(_stmt, "device_label")
        val _result: MutableList<CheckInSummaryEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CheckInSummaryEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpCreated_at: Long
          _tmpCreated_at = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpSummary_text: String
          _tmpSummary_text = _stmt.getText(_columnIndexOfSummaryText)
          val _tmpPatient_name: String?
          if (_stmt.isNull(_columnIndexOfPatientName)) {
            _tmpPatient_name = null
          } else {
            _tmpPatient_name = _stmt.getText(_columnIndexOfPatientName)
          }
          val _tmpClinic_name: String?
          if (_stmt.isNull(_columnIndexOfClinicName)) {
            _tmpClinic_name = null
          } else {
            _tmpClinic_name = _stmt.getText(_columnIndexOfClinicName)
          }
          val _tmpAppointment_type: String?
          if (_stmt.isNull(_columnIndexOfAppointmentType)) {
            _tmpAppointment_type = null
          } else {
            _tmpAppointment_type = _stmt.getText(_columnIndexOfAppointmentType)
          }
          val _tmpReason_for_visit: String?
          if (_stmt.isNull(_columnIndexOfReasonForVisit)) {
            _tmpReason_for_visit = null
          } else {
            _tmpReason_for_visit = _stmt.getText(_columnIndexOfReasonForVisit)
          }
          val _tmpDevice_label: String?
          if (_stmt.isNull(_columnIndexOfDeviceLabel)) {
            _tmpDevice_label = null
          } else {
            _tmpDevice_label = _stmt.getText(_columnIndexOfDeviceLabel)
          }
          _item =
              CheckInSummaryEntity(_tmpId,_tmpCreated_at,_tmpSummary_text,_tmpPatient_name,_tmpClinic_name,_tmpAppointment_type,_tmpReason_for_visit,_tmpDevice_label)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun count(): Int {
    val _sql: String = "SELECT COUNT(*) FROM checkin_ai_summaries"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _result: Int
        if (_stmt.step()) {
          val _tmp: Int
          _tmp = _stmt.getLong(0).toInt()
          _result = _tmp
        } else {
          _result = 0
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun delete(id: String) {
    val _sql: String = "DELETE FROM checkin_ai_summaries WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun clear() {
    val _sql: String = "DELETE FROM checkin_ai_summaries"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
