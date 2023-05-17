import {DataGrid, GRID_DEFAULT_LOCALE_TEXT, GridToolbar} from '@mui/x-data-grid';
import {customLocaleText} from "../app/dataGridTranslations";
import React, {useEffect, useState} from "react";
import {CircularProgress} from "@mui/material";

const DataTable = ({ data, columns, sortableColumns }) => {
    const [localeText, setLocaleText] = useState(null);

    useEffect(() => {
        import('../app/dataGridTranslations').then((module) => {
            setLocaleText(module.customLocaleText);
        });
    }, []);

    const columnsWithSorting = columns.map((column) => {
        if (sortableColumns.includes(column.field)) {
            return { ...column, sortable: true };
        }

        return { ...column, sortable: false };
    });

    if (!localeText) {
        return <CircularProgress />;
    }


    return (
        <div style={{ height: 400, width: '100%' }}>
            <DataGrid
                rows={data}
                columns={columnsWithSorting}
                sortingOrder={['asc', 'desc']} // Set the sorting order
                disableColumnMenu // Disable the column menu
                disableColumnSelector // Disable the column selector
                slots={{
                    toolbar: GridToolbar,
                }}
                localeText={ localeText }
            />
        </div>
    );
};

export default DataTable;
