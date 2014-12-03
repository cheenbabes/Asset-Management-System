/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.googlecharts;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.ValueType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class GoogleChartDBImpl implements GoogleChartsDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_GET_ASSETS_DAMAGED_BY_ITEM //chartA
            = "Select c.category_name, count(an.asset_note_id) AS countA "
            + "FROM categories AS c "
            + "INNER JOIN asset_types AS at "
            + "ON c.category_id = at.category_id "
            + "INNER JOIN assets AS a "
            + "ON at.asset_type_id = a.asset_type_id "
            + "INNER JOIN asset_notes AS an "
            + "ON a.asset_id = an.asset_id "
            + "WHERE note_category = 'Damage: 1' "
            + "OR note_category = 'Damage: 2' "
            + "OR note_category = 'Damage: 3' "
            + "OR note_category = 'Damage: 4' "
            + "OR note_category = 'Damage: 5' "
            + "AND note_date > 6/30/2014 "
            + "GROUP BY c.category_name";

    private static final String SQL_GET_ASSETS_DAMAGED_BY_USER //chartB
            = "SELECT u.username, Count(damage_loss_theft) AS countB "
            + "FROM users AS u "
            + "INNER JOIN events AS e "
            + "ON u.user_id = e.user_id "
            + "INNER JOIN assets_events AS ae "
            + "ON e.event_id = ae.event_id "
            + "WHERE ae.damage_loss_theft = 'Damage: 1' "
            + "OR ae.damage_loss_theft = 'Damage: 2' "
            + "OR ae.damage_loss_theft = 'Damage: 3' "
            + "OR ae.damage_loss_theft = 'Damage: 4' "
            + "OR ae.damage_loss_theft = 'Damage: 5' "
            + "GROUP BY e.user_id";

    private static final String SQL_GET_SEASONAL_UTILIZATION_RATE //chartC (doesn't work)
            = "SELECT COUNT(return_date) AS countC, MONTH(ae.return_date) AS monthC, c.category_name "
            + "FROM categories AS c "
            + "INNER JOIN asset_types AS at "
            + "ON c.category_id = at.category_id "
            + "INNER JOIN assets AS a "
            + "ON at.asset_type_id = a.asset_type_id "
            + "INNER JOIN assets_events AS ae "
            + "ON a.asset_id = ae.asset_id ";

    private static final String SQL_GET_UTILIZATION_EFFICIENCY //chartD
            = "SELECT at.name, SUM(CASE WHEN ae.return_date = 'NULL' THEN DATEDIFF(NOW(), e.check_out_date) ELSE DATEDIFF(ae.return_date, e.check_out_date) END) AS calcD "
            + "FROM categories AS c "
            + "INNER JOIN asset_types AS at "
            + "ON c.category_id = at.category_id "
            + "INNER JOIN assets AS a "
            + "ON at.asset_type_id = a.asset_type_id "
            + "INNER JOIN assets_events AS ae "
            + "ON a.asset_id = ae.asset_id "
            + "INNER JOIN events AS e "
            + "ON ae.event_id = e.event_id "
            + "WHERE check_out_date > 6/30/2014 "
            + "GROUP BY at.name";

    private static final String SQL_GET_CUSTOMER_EVENT_AVERAGE //chartE
            = "SELECT u.username, count(e.event_name) AS countE "
            + "FROM events AS e "
            + "INNER JOIN users AS u "
            + "ON e.user_id = u.user_id "
            + "WHERE check_out_date > 6/30/2014 "
            + "GROUP BY username";

    private static final String SQL_GET_CATEGORY_EVENT_AVERAGE //chartF 
            = "SELECT c.category_name, (COUNT(distinct a.asset_id)/(select count(*) from events)) AS countF "
            + "FROM categories AS c "
            + "INNER JOIN asset_types AS at "
            + "ON c.category_id = at.category_id "
            + "INNER JOIN assets AS a "
            + "ON at.asset_type_id = a.asset_type_id "
            + "INNER JOIN assets_events AS ae "
            + "ON a.asset_id = ae.asset_id "
            + "INNER JOIN events AS e "
            + "ON ae.event_id = e.event_id "
            + "GROUP BY c.category_name";

    private static final String SQL_GET_LOSS_THEFT_PER_PERSON //chartG
            = "SELECT u.username, COUNT(damage_loss_theft) AS countG "
            + "FROM users AS u "
            + "INNER JOIN events AS e "
            + "ON u.user_id = e.user_id "
            + "INNER JOIN assets_events AS ae "
            + "ON e.event_id = ae.event_id "
            + "WHERE ae.damage_loss_theft = 'Lost' "
            + "OR ae.damage_loss_theft = 'Stolen' "
            + "GROUP BY u.username";

    private static final String SQL_GET_LOSS_THEFT_PER_CATEGORY //chartH
            = "SELECT c.category_name, COUNT(note_category) AS countH "
            + "FROM categories AS c "
            + "INNER JOIN asset_types AS at "
            + "ON c.category_id = at.category_id "
            + "INNER JOIN assets AS a "
            + "ON at.asset_type_id = a.asset_type_id "
            + "INNER JOIN asset_notes AS an "
            + "ON a.asset_id = an.asset_id "
            + "WHERE note_category = 'Lost' "
            + "OR note_category = 'Stolen' "
            + "GROUP BY c.category_name ";

    private static final String SQL_GET_LATE_ITEMS_BY_USER //chartI
            = "SELECT u.username, COUNT(return_date) AS countI "
            + "FROM users AS u "
            + "INNER JOIN events AS e "
            + "ON u.user_id = e.user_id "
            + "INNER JOIN assets_events AS ae "
            + "ON e.event_id = ae.event_id "
            + "WHERE e.due_date < ae.return_date "
            + "GROUP BY u.username";

    private static final String SQL_GET_RATE_OF_LATENESS //chartJ
            = "SELECT e.event_name, AVG(DATEDIFF(return_date, due_date)) AS calcJ "
            + "FROM events AS e "
            + "INNER JOIN assets_events AS ae "
            + "ON e.event_id = ae.event_id "
            + "WHERE e.due_date < ae.return_date "
            + "GROUP BY e.event_name";

    @Override
    public DataTable getChartA() {
        // Create a data table.
        DataTable data = new DataTable();
        ArrayList<ColumnDescription> cd = new ArrayList<ColumnDescription>();
        cd.add(new ColumnDescription("c.category_name", ValueType.TEXT, "Category Name"));
        cd.add(new ColumnDescription("an.asset_note_id", ValueType.NUMBER, "Damaged"));
        data.addColumns(cd);

        try {
            List<TableRow> trList = jdbcTemplate.query(SQL_GET_ASSETS_DAMAGED_BY_ITEM, new ChartARowMapper());
            data.addRows(trList);
        } catch (TypeMismatchException e) {
            //do nothing returning an empty table below
        }
        return data;
    }

    private static class ChartARowMapper implements RowMapper<TableRow> {

        public TableRow mapRow(ResultSet rs, int i) throws SQLException {
            //convert ResultSet values to TableRow values
            TableRow tr = new TableRow();
            tr.addCell(rs.getString("c.category_name"));
            tr.addCell(rs.getInt("countA"));
            return tr;
        }
    }

    @Override
    public DataTable getChartB() {
        DataTable data = new DataTable();
        ArrayList<ColumnDescription> cd = new ArrayList<ColumnDescription>();
        cd.add(new ColumnDescription("User Name", ValueType.TEXT, "User Name"));
        cd.add(new ColumnDescription("Count of damaged", ValueType.NUMBER, "Count of Damaged"));
        data.addColumns(cd);

        try {
            List<TableRow> trList = jdbcTemplate.query(SQL_GET_ASSETS_DAMAGED_BY_USER, new ChartBRowMapper());
            data.addRows(trList);
        } catch (TypeMismatchException e) {
            //do nothing returning an empty table below
        }
        return data;
    }

    private static class ChartBRowMapper implements RowMapper<TableRow> {

        public TableRow mapRow(ResultSet rs, int i) throws SQLException {
            TableRow tr = new TableRow();
            tr.addCell(rs.getString("u.username"));
            tr.addCell(rs.getInt("countB"));
            return tr;
        }
    }

//    @Override
//    public DataTable getChartC() {
//        DataTable data = new DataTable();
//        ArrayList<ColumnDescription> cd = new ArrayList<ColumnDescription>();
//        cd.addColumn(new ColumnDescription("count", ValueType.TEXT, "month"));
//        cd.addColumn(new ColumnDescription("canoe", ValueType.NUMBER, "canoe"));
//        cd.addColumn(new ColumnDescription("paddle", ValueType.NUMBER, "paddle"));
//        data.addColumns(cd);
//        return data;
//    }
//        try {
//            List<TableRow> trList = jdbcTemplate.query(SQL_GET_SEASONAL_UTILIZATION_RATE, new ChartCRowMapper());
//            data.addRows(trList);
//        } catch (TypeMismatchException e) {
//            //do nothing returning an empty table below
//        }
//        return data;
//    }
//    private static class ChartCRowMapper implements RowMapper<TableRow> {
//
//        public TableRow mapRow(ResultSet rs, int i) throws SQLException {
//            TableRow tr = new TableRow();
//            tr.addCell(rs.getString("monthC"));
//            tr.addCell(rs.getInt("countC"));
//            //tr.addCell(rs.getString("c.category_name"));
//            return tr;
//        }
//    }
    @Override
    public DataTable getChartD() {
        DataTable data = new DataTable();
        ArrayList<ColumnDescription> cd = new ArrayList<ColumnDescription>();
        cd.add(new ColumnDescription("Category", ValueType.TEXT, "Category"));
        cd.add(new ColumnDescription("Percent Util", ValueType.NUMBER, "Percent Util"));
        data.addColumns(cd);

        try {
            List<TableRow> trList = jdbcTemplate.query(SQL_GET_UTILIZATION_EFFICIENCY, new ChartDRowMapper());
            data.addRows(trList);
        } catch (TypeMismatchException e) {
            //do nothing returning an empty table below
        }
        return data;
    }

    private static class ChartDRowMapper implements RowMapper<TableRow> {

        public TableRow mapRow(ResultSet rs, int i) throws SQLException {
            TableRow tr = new TableRow();
            tr.addCell(rs.getString("at.name"));
            tr.addCell(rs.getInt("calcD"));
            return tr;
        }
    }

    @Override
    public DataTable getChartE() {
        DataTable data = new DataTable();
        ArrayList<ColumnDescription> cd = new ArrayList<ColumnDescription>();
        cd.add(new ColumnDescription("User Name", ValueType.TEXT, "User Name"));
        cd.add(new ColumnDescription("Count of events", ValueType.NUMBER, "Count of events"));
        data.addColumns(cd);

        try {
            List<TableRow> trList = jdbcTemplate.query(SQL_GET_CUSTOMER_EVENT_AVERAGE, new ChartERowMapper());
            data.addRows(trList);
        } catch (TypeMismatchException e) {
            //do nothing returning an empty table below
        }
        return data;
    }

    private static class ChartERowMapper implements RowMapper<TableRow> {

        public TableRow mapRow(ResultSet rs, int i) throws SQLException {
            TableRow tr = new TableRow();
            tr.addCell(rs.getString("u.username"));
            tr.addCell(rs.getInt("countE"));
            return tr;
        }
    }

    @Override
    public DataTable getChartF() {
        DataTable data = new DataTable();
        ArrayList<ColumnDescription> cd = new ArrayList<ColumnDescription>();
        cd.add(new ColumnDescription("c.category_name", ValueType.TEXT, "Category Name"));
        cd.add(new ColumnDescription("count per event", ValueType.NUMBER, "Count"));
        data.addColumns(cd);

        try {
            List<TableRow> trList = jdbcTemplate.query(SQL_GET_CATEGORY_EVENT_AVERAGE, new ChartFRowMapper());
            data.addRows(trList);
        } catch (TypeMismatchException e) {
            //do nothing returning an empty table below
        }
        return data;
    }

    private static class ChartFRowMapper implements RowMapper<TableRow> {

        public TableRow mapRow(ResultSet rs, int i) throws SQLException {
            //convert ResultSet values to TableRow values
            TableRow tr = new TableRow();
            tr.addCell(rs.getString("c.category_name"));
            tr.addCell(rs.getDouble("countF"));
            return tr;
        }
    }

    @Override
    public DataTable getChartG() {
        DataTable data = new DataTable();
        ArrayList<ColumnDescription> cd = new ArrayList<ColumnDescription>();
        cd.add(new ColumnDescription("User", ValueType.TEXT, "User"));
        cd.add(new ColumnDescription("Count damage", ValueType.NUMBER, "Count damage"));
        data.addColumns(cd);

        try {
            List<TableRow> trList = jdbcTemplate.query(SQL_GET_LOSS_THEFT_PER_PERSON, new ChartGRowMapper());
            data.addRows(trList);
        } catch (TypeMismatchException e) {
            //do nothing returning an empty table below
        }
        return data;
    }

    private static class ChartGRowMapper implements RowMapper<TableRow> {

        public TableRow mapRow(ResultSet rs, int i) throws SQLException {
            //convert ResultSet values to TableRow values
            TableRow tr = new TableRow();
            tr.addCell(rs.getString("u.username"));
            tr.addCell(rs.getInt("countG"));

            return tr;
        }
    }

    @Override
    public DataTable getChartH() {
        DataTable data = new DataTable();
        ArrayList<ColumnDescription> cd = new ArrayList<ColumnDescription>();
        cd.add(new ColumnDescription("Category", ValueType.TEXT, "Category"));
        cd.add(new ColumnDescription("Count of loss_theft", ValueType.NUMBER, "Count of loss_theft"));
        data.addColumns(cd);

        try {
            List<TableRow> trList = jdbcTemplate.query(SQL_GET_LOSS_THEFT_PER_CATEGORY, new ChartHRowMapper());
            data.addRows(trList);
        } catch (TypeMismatchException e) {
            //do nothing returning an empty table below
        }
        return data;
    }

    private static class ChartHRowMapper implements RowMapper<TableRow> {

        public TableRow mapRow(ResultSet rs, int i) throws SQLException {
            TableRow tr = new TableRow();
            tr.addCell(rs.getString("c.category_name"));
            tr.addCell(rs.getInt("countH"));
            return tr;
        }
    }

    @Override
    public DataTable getChartI() {
        DataTable data = new DataTable();
        ArrayList<ColumnDescription> cd = new ArrayList<ColumnDescription>();
        cd.add(new ColumnDescription("User", ValueType.TEXT, "User"));
        cd.add(new ColumnDescription("Count of late items", ValueType.NUMBER, "Count of late items"));
        data.addColumns(cd);

        try {
            List<TableRow> trList = jdbcTemplate.query(SQL_GET_LATE_ITEMS_BY_USER, new ChartIRowMapper());
            data.addRows(trList);
        } catch (TypeMismatchException e) {
            //do nothing returning an empty table below
        }
        return data;
    }

    private static class ChartIRowMapper implements RowMapper<TableRow> {

        public TableRow mapRow(ResultSet rs, int i) throws SQLException {
            TableRow tr = new TableRow();
            tr.addCell(rs.getString("u.username"));
            tr.addCell(rs.getInt("countI"));
            return tr;
        }
    }

    @Override
    public DataTable getChartJ() {
        DataTable data = new DataTable();
        ArrayList<ColumnDescription> cd = new ArrayList<ColumnDescription>();
        cd.add(new ColumnDescription("Event", ValueType.TEXT, "Event Name"));
        cd.add(new ColumnDescription("Days Late", ValueType.NUMBER, "Days Late"));
        data.addColumns(cd);

        try {
            List<TableRow> trList = jdbcTemplate.query(SQL_GET_RATE_OF_LATENESS, new ChartJRowMapper());
            data.addRows(trList);
        } catch (TypeMismatchException e) {
            //do nothing returning an empty table below
        }
        return data;
    }

    private static class ChartJRowMapper implements RowMapper<TableRow> {

        public TableRow mapRow(ResultSet rs, int i) throws SQLException {
            TableRow tr = new TableRow();
            tr.addCell(rs.getString("e.event_name"));
            tr.addCell(rs.getInt("calcJ"));
            return tr;
        }
    }
}
