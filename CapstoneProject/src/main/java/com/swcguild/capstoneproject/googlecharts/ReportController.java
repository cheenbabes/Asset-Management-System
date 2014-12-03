package com.swcguild.capstoneproject.googlecharts;

import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.ValueType;
import com.google.visualization.datasource.render.JsonRenderer;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/reports"})
public class ReportController {

    private GoogleChartsDao dao;

    @Inject
    public ReportController(GoogleChartsDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/chartA", method = RequestMethod.GET)
    @ResponseBody
    public String getTableA() {
        DataTable t = dao.getChartA();
        return JsonRenderer.renderDataTable(t, true, false, false).toString();
    }

    @RequestMapping(value = "/chartB", method = RequestMethod.GET)
    @ResponseBody
    public String getTableB() {
        DataTable t = dao.getChartB();
        return JsonRenderer.renderDataTable(t, true, false, false).toString();
    }
    
//    @RequestMapping(value = "/chartC", method = RequestMethod.GET)
//    @ResponseBody
//    public String getTableC() {
//        DataTable t = dao.getChartB();
//        return JsonRenderer.renderDataTable(t, true, false, false).toString();
//    }

    @RequestMapping(value = "/chartC", method = RequestMethod.GET)
    @ResponseBody
    public String getTableC() {
        try {
            DataTable t = new DataTable();
            t.addColumn(new ColumnDescription("count", ValueType.TEXT, "month"));
            t.addColumn(new ColumnDescription("canoe", ValueType.NUMBER, "canoe"));
            t.addColumn(new ColumnDescription("paddle", ValueType.NUMBER, "paddle"));
            TableRow tr = new TableRow();
            tr.addCell("1");
            tr.addCell(43);
            tr.addCell(45);
            t.addRow(tr);
            tr = new TableRow();
            tr.addCell("2");
            tr.addCell(21);
            tr.addCell(30);
            t.addRow(tr);
            tr = new TableRow();
            tr.addCell("3");
            tr.addCell(11);
            tr.addCell(20);
            t.addRow(tr);
            tr = new TableRow();
            tr.addCell("4");
            tr.addCell(41);
            tr.addCell(38);
            t.addRow(tr);
            tr = new TableRow();
            tr.addCell("5");
            tr.addCell(29);
            tr.addCell(60);
            t.addRow(tr);

            return JsonRenderer.renderDataTable(t, true, false, false).toString();
        } catch (Exception e) {
            return "Invalid Data";
        }
    }

    @RequestMapping(value = "/chartD", method = RequestMethod.GET)
    @ResponseBody
    public String getTableD() {
        DataTable t = dao.getChartD();
        return JsonRenderer.renderDataTable(t, true, false, false).toString();
    }

    @RequestMapping(value = "/chartE", method = RequestMethod.GET)
    @ResponseBody
    public String getTableE() {
        DataTable t = dao.getChartE();
        return JsonRenderer.renderDataTable(t, true, false, false).toString();
    }

    @RequestMapping(value = "/chartF", method = RequestMethod.GET)
    @ResponseBody
    public String getTableF() {
        DataTable t = dao.getChartF();
        return JsonRenderer.renderDataTable(t, true, false, false).toString();
    }

    @RequestMapping(value = "/chartG", method = RequestMethod.GET)
    @ResponseBody
    public String getTableG() {
        DataTable t = dao.getChartG();
        return JsonRenderer.renderDataTable(t, true, false, false).toString();
    }

    @RequestMapping(value = "/chartH", method = RequestMethod.GET)
    @ResponseBody
    public String getTableH() {
        DataTable t = dao.getChartH();
        return JsonRenderer.renderDataTable(t, true, false, false).toString();
    }

    @RequestMapping(value = "/chartI", method = RequestMethod.GET)
    @ResponseBody
    public String getTableI() {
        DataTable t = dao.getChartI();
        return JsonRenderer.renderDataTable(t, true, false, false).toString();
    }

    @RequestMapping(value = "/chartJ", method = RequestMethod.GET)
    @ResponseBody
    public String getTableJ() {
        DataTable t = dao.getChartJ();
        return JsonRenderer.renderDataTable(t, true, false, false).toString();
    }
}
