export const customLocaleText = {
    // Root
    noRowsLabel: 'Nav rindu',
    noResultsOverlayLabel: 'Nav rezultātu.',

    // Density selector toolbar button text
    toolbarDensity: 'Blīvums',
    toolbarDensityLabel: 'Blīvums',
    toolbarDensityCompact: 'Kompakts',
    toolbarDensityStandard: 'Standarta',
    toolbarDensityComfortable: 'Ērts',

    // Columns selector toolbar button text
    toolbarColumns: 'Kolonnas',
    toolbarColumnsLabel: 'Atlasīt kolonnas',

    // Filters toolbar button text
    toolbarFilters: 'Filtri',
    toolbarFiltersLabel: 'Rādīt filtrus',
    toolbarFiltersTooltipHide: 'Paslēpt filtrus',
    toolbarFiltersTooltipShow: 'Rādīt filtrus',
    toolbarFiltersTooltipActive: (count) =>
        count !== 1 ? `${count} aktīvi filtri` : `${count} aktīvs filtrs`,

    // Quick filter toolbar field
    toolbarQuickFilterPlaceholder: 'Meklēt...',
    toolbarQuickFilterLabel: 'Meklēt',
    toolbarQuickFilterDeleteIconLabel: 'Notīrīt',

    // Export selector toolbar button text
    toolbarExport: 'Lejupielādēt',
    toolbarExportLabel: 'Lejupielādēt',
    toolbarExportCSV: 'Lejupielādēt kā CSV',
    toolbarExportPrint: 'Drukāt',
    toolbarExportExcel: 'Lejupielādēt kā Excel',

    // Columns panel text
    columnsPanelTextFieldLabel: 'Meklēt kolonnu',
    columnsPanelTextFieldPlaceholder: 'Kolonnu nosaukums',
    columnsPanelDragIconLabel: 'Mainīt kolonnas secību',
    columnsPanelShowAllButton: 'Rādīt visu',
    columnsPanelHideAllButton: 'Paslēpt visu',

    // Filter panel text
    filterPanelAddFilter: 'Pievienot filtru',
    filterPanelRemoveAll: 'Noņemt visus',
    filterPanelDeleteIconLabel: 'Dzēst',
    filterPanelLogicOperator: 'Loģiskais operators',
    filterPanelOperator: 'Operators',
    filterPanelOperatorAnd: 'Un',
    filterPanelOperatorOr: 'Vai',
    filterPanelColumns: 'Kolonnas',
    filterPanelInputLabel: 'Vērtība',
    filterPanelInputPlaceholder: 'Filtra vērtība',

    // Filter operators text
    filterOperatorContains: 'satur',
    filterOperatorEquals: 'vienāds',
    filterOperatorStartsWith: 'sākas ar',
    filterOperatorEndsWith: 'beidzas ar',
    filterOperatorIs: 'ir',
    filterOperatorNot: 'nav',
    filterOperatorAfter: 'ir pēc',
    filterOperatorOnOrAfter: 'ir vai pēc',
    filterOperatorBefore: 'ir pirms',
    filterOperatorOnOrBefore: 'ir vai pirms',
    filterOperatorIsEmpty: 'ir tukšs',
    filterOperatorIsNotEmpty: 'nav tukšs',
    filterOperatorIsAnyOf: 'ir jebkura no',

    // Filter values text
    filterValueAny: 'jebkura',
    filterValueTrue: 'patiess',
    filterValueFalse: 'nepatiess',

    // Column menu text
    columnMenuLabel: 'Izvēlne',
    columnMenuShowColumns: 'Rādīt kolonnas',
    columnMenuManageColumns: 'Pārvaldīt kolonnas',
    columnMenuFilter: 'Filtrs',
    columnMenuHideColumn: 'Paslēpt kolonnu',
    columnMenuUnsort: 'Noņemt kārtošanu',
    columnMenuSortAsc: 'Kārtot pieaugošā secībā',
    columnMenuSortDesc: 'Kārtot dilstošā secībā',

    // Column header text
    columnHeaderFiltersTooltipActive: (count) =>
        count !== 1 ? `${count} aktīvi filtri` : `${count} aktīvs filtrs`,
    columnHeaderFiltersLabel: 'Rādīt filtrus',
    columnHeaderSortIconLabel: 'Kārtošana',

    // Rows selected footer text
    footerRowSelected: (count) =>
        count !== 1
            ? `${count.toLocaleString()} atlasītas rindas`
            : `${count.toLocaleString()} atlasīta rinda`,

    // Total row amount footer text
    footerTotalRows: 'Kopā rindas:',

    // Row reordering text
    rowReorderingHeaderName: 'Mainīt rindu secību',

    // Aggregation
    aggregationMenuItemHeader: 'Apkopojums',
    aggregationFunctionLabelSum: 'summa',
    aggregationFunctionLabelAvg: 'vidējais',
    aggregationFunctionLabelMin: 'minimālais',
    aggregationFunctionLabelMax: 'maksimālais',
    aggregationFunctionLabelSize: 'izmērs',

    footerTotalVisibleRows: (visibleCount, totalCount) =>
        `${visibleCount.toLocaleString()} no ${totalCount.toLocaleString()}`,

    // Checkbox selection text
    checkboxSelectionHeaderName: 'Atlasīšana',
    checkboxSelectionSelectAllRows: 'Atlasīt visas rindas',
    checkboxSelectionUnselectAllRows: 'Noņemt atlasījumu no visām rindām',
    checkboxSelectionSelectRow: 'Atlasīt rindu',
    checkboxSelectionUnselectRow: 'Noņemt atlasījumu no rindas',

    // Boolean cell text
    booleanCellTrueLabel: 'jā',
    booleanCellFalseLabel: 'nē',

    // Actions cell more text
    actionsCellMore: 'vairāk',

    // Column pinning text
    pinToLeft: 'Piespraust pa kreisi',
    pinToRight: 'Piespraust pa labi',
    unpin: 'Noņemt piespraustu stāvokli',

    // Tree Data
    treeDataGroupingHeaderName: 'Grupēt',
    treeDataExpand: 'Rādīt bērnus',
    treeDataCollapse: 'Paslēpt bērnus',

    // Grouping columns
    groupingColumnHeaderName: 'Grupēt',
    groupColumn: (name) => `Grupēt pēc ${name}`,
    unGroupColumn: (name) => `Pārtraukt grupēšanu pēc ${name}`,

    // Master/detail
    detailPanelToggle: 'Pārslēgt detaļu paneli',
    expandDetailPanel: 'Izvērst',
    collapseDetailPanel: 'Sakļaut',

    // Used core components translation keys
    MuiTablePagination: {
        labelRowsPerPage: 'Rindas uz lapu:',
        labelDisplayedRows: ({ from, to, count }) => `${from}-${to} no ${count !== -1 ? count : to}`,
    },
};
