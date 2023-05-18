import {DataGrid, GRID_DEFAULT_LOCALE_TEXT, GridToolbar} from '@mui/x-data-grid';
import {customLocaleText} from "../app/dataGridTranslations";
import React, {useEffect, useState} from "react";
import {CircularProgress} from "@mui/material";
import {Grid3x3Outlined, GridGoldenratioOutlined} from "@mui/icons-material";

const DataTable = ({ data, columns, sortableColumns }) => {
    const [localeText, setLocaleText] = useState(null);

    useEffect(() => {
        import('../app/dataGridTranslations').then((module) => {
            setLocaleText(module.customLocaleText);
        });
    }, []);

    const dataColumns = columns.map((column) => {
        if (sortableColumns.includes(column.field)) {
            return { ...column, sortable: true };
        }

        return { ...column, sortable: false };
    });

    if (!localeText) {
        return <CircularProgress />;
    }


    return (
        <div className="data-table">
            <DataGrid
                rows={data}
                columns={dataColumns}
                sortingOrder={['asc', 'desc']}
                disableColumnMenu
                disableColumnSelector
                disableColumnResize={false}
                slots={{
                    toolbar: GridToolbar,
                    resizeContainer: GridGoldenratioOutlined,
                }}
                localeText={localeText}
                autoHeight
            />
        </div>
    );
};

export default DataTable;
