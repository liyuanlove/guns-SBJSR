package com.stylefeng.guns.generator.po;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Proxy(lazy = false)
@Table(name = "TABLES", schema = "information_schema", catalog = "")
public class Tables {
    private String tableCatalog;
    private String tableSchema;
    @Id
    private String tableName;
    private String tableType;
    private String engine;
    private Long version;
    private String rowFormat;
    private Long tableRows;
    private Long avgRowLength;
    private Long dataLength;
    private Long maxDataLength;
    private Long indexLength;
    private Long dataFree;
    private Long autoIncrement;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Timestamp checkTime;
    private String tableCollation;
    private Long checksum;
    private String createOptions;
    private String tableComment;

    @Basic
    @Column(name = "TABLE_CATALOG")
    public String getTableCatalog() {
        return tableCatalog;
    }

    public void setTableCatalog(String tableCatalog) {
        this.tableCatalog = tableCatalog;
    }

    @Basic
    @Column(name = "TABLE_SCHEMA")
    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    @Basic
    @Column(name = "TABLE_NAME")
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Basic
    @Column(name = "TABLE_TYPE")
    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    @Basic
    @Column(name = "ENGINE")
    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    @Basic
    @Column(name = "VERSION")
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Basic
    @Column(name = "ROW_FORMAT")
    public String getRowFormat() {
        return rowFormat;
    }

    public void setRowFormat(String rowFormat) {
        this.rowFormat = rowFormat;
    }

    @Basic
    @Column(name = "TABLE_ROWS")
    public Long getTableRows() {
        return tableRows;
    }

    public void setTableRows(Long tableRows) {
        this.tableRows = tableRows;
    }

    @Basic
    @Column(name = "AVG_ROW_LENGTH")
    public Long getAvgRowLength() {
        return avgRowLength;
    }

    public void setAvgRowLength(Long avgRowLength) {
        this.avgRowLength = avgRowLength;
    }

    @Basic
    @Column(name = "DATA_LENGTH")
    public Long getDataLength() {
        return dataLength;
    }

    public void setDataLength(Long dataLength) {
        this.dataLength = dataLength;
    }

    @Basic
    @Column(name = "MAX_DATA_LENGTH")
    public Long getMaxDataLength() {
        return maxDataLength;
    }

    public void setMaxDataLength(Long maxDataLength) {
        this.maxDataLength = maxDataLength;
    }

    @Basic
    @Column(name = "INDEX_LENGTH")
    public Long getIndexLength() {
        return indexLength;
    }

    public void setIndexLength(Long indexLength) {
        this.indexLength = indexLength;
    }

    @Basic
    @Column(name = "DATA_FREE")
    public Long getDataFree() {
        return dataFree;
    }

    public void setDataFree(Long dataFree) {
        this.dataFree = dataFree;
    }

    @Basic
    @Column(name = "AUTO_INCREMENT")
    public Long getAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(Long autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    @Basic
    @Column(name = "CREATE_TIME")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "UPDATE_TIME")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "CHECK_TIME")
    public Timestamp getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Timestamp checkTime) {
        this.checkTime = checkTime;
    }

    @Basic
    @Column(name = "TABLE_COLLATION")
    public String getTableCollation() {
        return tableCollation;
    }

    public void setTableCollation(String tableCollation) {
        this.tableCollation = tableCollation;
    }

    @Basic
    @Column(name = "CHECKSUM")
    public Long getChecksum() {
        return checksum;
    }

    public void setChecksum(Long checksum) {
        this.checksum = checksum;
    }

    @Basic
    @Column(name = "CREATE_OPTIONS")
    public String getCreateOptions() {
        return createOptions;
    }

    public void setCreateOptions(String createOptions) {
        this.createOptions = createOptions;
    }

    @Basic
    @Column(name = "TABLE_COMMENT")
    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tables tables = (Tables) o;
        return Objects.equals(tableCatalog, tables.tableCatalog) &&
                Objects.equals(tableSchema, tables.tableSchema) &&
                Objects.equals(tableName, tables.tableName) &&
                Objects.equals(tableType, tables.tableType) &&
                Objects.equals(engine, tables.engine) &&
                Objects.equals(version, tables.version) &&
                Objects.equals(rowFormat, tables.rowFormat) &&
                Objects.equals(tableRows, tables.tableRows) &&
                Objects.equals(avgRowLength, tables.avgRowLength) &&
                Objects.equals(dataLength, tables.dataLength) &&
                Objects.equals(maxDataLength, tables.maxDataLength) &&
                Objects.equals(indexLength, tables.indexLength) &&
                Objects.equals(dataFree, tables.dataFree) &&
                Objects.equals(autoIncrement, tables.autoIncrement) &&
                Objects.equals(createTime, tables.createTime) &&
                Objects.equals(updateTime, tables.updateTime) &&
                Objects.equals(checkTime, tables.checkTime) &&
                Objects.equals(tableCollation, tables.tableCollation) &&
                Objects.equals(checksum, tables.checksum) &&
                Objects.equals(createOptions, tables.createOptions) &&
                Objects.equals(tableComment, tables.tableComment);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tableCatalog, tableSchema, tableName, tableType, engine, version, rowFormat, tableRows, avgRowLength, dataLength, maxDataLength, indexLength, dataFree, autoIncrement, createTime, updateTime, checkTime, tableCollation, checksum, createOptions, tableComment);
    }
}
