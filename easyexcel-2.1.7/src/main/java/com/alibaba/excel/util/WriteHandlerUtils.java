package com.alibaba.excel.util;

import com.alibaba.excel.context.WriteContext;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * Write handler utils
 *
 * @author Jiaju Zhuang
 */
public class WriteHandlerUtils {

    private WriteHandlerUtils() {}

    public static void beforeWorkbookCreate(WriteContext writeContext) {
        List<WriteHandler> handlerList =
            writeContext.writeWorkbookHolder().writeHandlerMap().get(WorkbookWriteHandler.class);
        if (handlerList == null || handlerList.isEmpty()) {
            return;
        }
        for (WriteHandler writeHandler : handlerList) {
            if (writeHandler instanceof WorkbookWriteHandler) {
                // Force-EasyExcel 拓展点：WorkbookWriteHandler.beforeWorkbookCreate
                ((WorkbookWriteHandler)writeHandler).beforeWorkbookCreate();
            }
        }
    }

    public static void afterWorkbookCreate(WriteContext writeContext) {
        List<WriteHandler> handlerList =
            writeContext.writeWorkbookHolder().writeHandlerMap().get(WorkbookWriteHandler.class);
        if (handlerList == null || handlerList.isEmpty()) {
            return;
        }
        for (WriteHandler writeHandler : handlerList) {
            if (writeHandler instanceof WorkbookWriteHandler) {
                // Force-EasyExcel 拓展点：WorkbookWriteHandler.afterWorkbookCreate
                ((WorkbookWriteHandler)writeHandler).afterWorkbookCreate(writeContext.writeWorkbookHolder());
            }
        }
    }

    public static void afterWorkbookDispose(WriteContext writeContext) {
        List<WriteHandler> handlerList =
            writeContext.writeWorkbookHolder().writeHandlerMap().get(WorkbookWriteHandler.class);
        if (handlerList == null || handlerList.isEmpty()) {
            return;
        }
        for (WriteHandler writeHandler : handlerList) {
            if (writeHandler instanceof WorkbookWriteHandler) {
                // Force-EasyExcel 拓展点：WorkbookWriteHandler.afterWorkbookDispose
                ((WorkbookWriteHandler)writeHandler).afterWorkbookDispose(writeContext.writeWorkbookHolder());
            }
        }
    }

    public static void beforeSheetCreate(WriteContext writeContext) {
        List<WriteHandler> handlerList = writeContext.writeSheetHolder().writeHandlerMap().get(SheetWriteHandler.class);
        if (handlerList == null || handlerList.isEmpty()) {
            return;
        }
        for (WriteHandler writeHandler : handlerList) {
            if (writeHandler instanceof SheetWriteHandler) {
                // Force-EasyExcel 拓展点：SheetWriteHandler.beforeSheetCreate
                ((SheetWriteHandler)writeHandler).beforeSheetCreate(writeContext.writeWorkbookHolder(),
                    writeContext.writeSheetHolder());
            }
        }
    }

    public static void afterSheetCreate(WriteContext writeContext) {
        List<WriteHandler> handlerList = writeContext.writeSheetHolder().writeHandlerMap().get(SheetWriteHandler.class);
        if (handlerList == null || handlerList.isEmpty()) {
            return;
        }
        for (WriteHandler writeHandler : handlerList) {
            if (writeHandler instanceof SheetWriteHandler) {
                // Force-EasyExcel 拓展点：SheetWriteHandler.afterSheetCreate
                ((SheetWriteHandler)writeHandler).afterSheetCreate(writeContext.writeWorkbookHolder(),
                    writeContext.writeSheetHolder());
            }
        }
        if (null != writeContext.writeWorkbookHolder().getWriteWorkbook().getWriteHandler()) {
            writeContext.writeWorkbookHolder().getWriteWorkbook().getWriteHandler()
                .sheet(writeContext.writeSheetHolder().getSheetNo(), writeContext.writeSheetHolder().getSheet());
        }
    }

    public static void beforeCellCreate(WriteContext writeContext, Row row, Head head, Integer columnIndex,
        Integer relativeRowIndex, Boolean isHead) {
        List<WriteHandler> handlerList =
            writeContext.currentWriteHolder().writeHandlerMap().get(CellWriteHandler.class);
        if (handlerList == null || handlerList.isEmpty()) {
            return;
        }
        for (WriteHandler writeHandler : handlerList) {
            if (writeHandler instanceof CellWriteHandler) {
                // Force-EasyExcel 拓展点：CellWriteHandler.beforeCellCreate
                ((CellWriteHandler)writeHandler).beforeCellCreate(writeContext.writeSheetHolder(),
                    writeContext.writeTableHolder(), row, head, columnIndex, relativeRowIndex, isHead);
            }
        }
    }

    public static void afterCellCreate(WriteContext writeContext, Cell cell, Head head, Integer relativeRowIndex,
        Boolean isHead) {
        List<WriteHandler> handlerList =
            writeContext.currentWriteHolder().writeHandlerMap().get(CellWriteHandler.class);
        if (handlerList == null || handlerList.isEmpty()) {
            return;
        }
        for (WriteHandler writeHandler : handlerList) {
            if (writeHandler instanceof CellWriteHandler) {
                // Force-EasyExcel 拓展点：CellWriteHandler.afterCellCreate
                ((CellWriteHandler)writeHandler).afterCellCreate(writeContext.writeSheetHolder(),
                    writeContext.writeTableHolder(), cell, head, relativeRowIndex, isHead);
            }
        }
    }

    public static void afterCellDispose(WriteContext writeContext, CellData cellData, Cell cell, Head head,
        Integer relativeRowIndex, Boolean isHead) {
        List<CellData> cellDataList = new ArrayList<CellData>();
        if (cell != null) {
            cellDataList.add(cellData);
        }
        afterCellDispose(writeContext, cellDataList, cell, head, relativeRowIndex, isHead);
    }

    public static void afterCellDispose(WriteContext writeContext, List<CellData> cellDataList, Cell cell, Head head,
        Integer relativeRowIndex, Boolean isHead) {
        List<WriteHandler> handlerList =
            writeContext.currentWriteHolder().writeHandlerMap().get(CellWriteHandler.class);
        if (handlerList == null || handlerList.isEmpty()) {
            return;
        }
        for (WriteHandler writeHandler : handlerList) {
            if (writeHandler instanceof CellWriteHandler) {
                // Force-EasyExcel 拓展点：CellWriteHandler.afterCellDispose
                ((CellWriteHandler)writeHandler).afterCellDispose(writeContext.writeSheetHolder(),
                    writeContext.writeTableHolder(), cellDataList, cell, head, relativeRowIndex, isHead);
            }
        }
        if (null != writeContext.writeWorkbookHolder().getWriteWorkbook().getWriteHandler()) {
            writeContext.writeWorkbookHolder().getWriteWorkbook().getWriteHandler().cell(cell.getRowIndex(), cell);
        }
    }

    public static void beforeRowCreate(WriteContext writeContext, Integer rowIndex, Integer relativeRowIndex,
        Boolean isHead) {
        List<WriteHandler> handlerList = writeContext.currentWriteHolder().writeHandlerMap().get(RowWriteHandler.class);
        if (handlerList == null || handlerList.isEmpty()) {
            return;
        }
        for (WriteHandler writeHandler : handlerList) {
            if (writeHandler instanceof RowWriteHandler) {
                // Force-EasyExcel 拓展点：RowWriteHandler.beforeRowCreate
                ((RowWriteHandler)writeHandler).beforeRowCreate(writeContext.writeSheetHolder(),
                    writeContext.writeTableHolder(), rowIndex, relativeRowIndex, isHead);
            }
        }
    }

    public static void afterRowCreate(WriteContext writeContext, Row row, Integer relativeRowIndex, Boolean isHead) {
        List<WriteHandler> handlerList = writeContext.currentWriteHolder().writeHandlerMap().get(RowWriteHandler.class);
        if (handlerList == null || handlerList.isEmpty()) {
            return;
        }
        for (WriteHandler writeHandler : handlerList) {
            if (writeHandler instanceof RowWriteHandler) {
                // Force-EasyExcel 拓展点：RowWriteHandler.afterRowCreate
                ((RowWriteHandler)writeHandler).afterRowCreate(writeContext.writeSheetHolder(),
                    writeContext.writeTableHolder(), row, relativeRowIndex, isHead);
            }
        }

    }

    public static void afterRowDispose(WriteContext writeContext, Row row, Integer relativeRowIndex, Boolean isHead) {
        List<WriteHandler> handlerList = writeContext.currentWriteHolder().writeHandlerMap().get(RowWriteHandler.class);
        if (handlerList == null || handlerList.isEmpty()) {
            return;
        }
        for (WriteHandler writeHandler : handlerList) {
            if (writeHandler instanceof RowWriteHandler) {
                // Force-EasyExcel 拓展点：RowWriteHandler.afterRowDispose
                ((RowWriteHandler)writeHandler).afterRowDispose(writeContext.writeSheetHolder(),
                    writeContext.writeTableHolder(), row, relativeRowIndex, isHead);
            }
        }
        if (null != writeContext.writeWorkbookHolder().getWriteWorkbook().getWriteHandler()) {
            writeContext.writeWorkbookHolder().getWriteWorkbook().getWriteHandler().row(row.getRowNum(), row);
        }
    }
}
