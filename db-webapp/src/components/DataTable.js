import { DataGrid, GridToolbar } from '@mui/x-data-grid';
import React, { useEffect, useState } from 'react';
import { GridGoldenratioOutlined } from '@mui/icons-material';

const DataTable = ({ data, columns, sortableColumns }) => {
    const [localeText, setLocaleText] = useState(null);

    useEffect(() => {
        import('../app/dataGridTranslations').then((module) => {
            setLocaleText(module.customLocaleText);
        });
    }, []);

    const dataColumns = columns.map((column) => {
        const isSortable = sortableColumns.includes(column.field);
        const isIDField = column.field === 'id';
        const isActionsField = column.field === 'actions';
        return {
            ...column,
            sortable: isSortable,
            flex: 1,
            filterable: !isIDField && !isActionsField,
            disableColumnMenu: isIDField || isActionsField,
            disableColumnSelector: isIDField || isActionsField,
            disableColumnResize: isIDField || isActionsField,
        };
    });

    return (
        <div className="data-table">
            <DataGrid
                rows={data}
                columns={dataColumns}
                sortingOrder={['asc', 'desc']}
                disableColumnMenu
                disableColumnSelector
                disableColumnResize={false}
                columnVisibilityModel={{
                    id: false
                }}
                GridColDef={{
                    resizable: true,
                }}
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
