import React, { useEffect, useState } from 'react';
import ClubService from "../../services/ClubService";
import ServerPaginationDataTable from "../../components/ServerPaginationDataTable";
import ConfirmationDialog from "../../components/dialogs/ConfirmationDialog";
import EditClubDialog from "../../components/dialogs/club/EditClubDialog";
import { toast } from "react-toastify";
import { IconButton } from "@mui/material";
import { Delete, Edit } from "@mui/icons-material";

const ClubsList = () => {
    const [clubList, setClubList] = useState([]);
    const [totalCount, setTotalCount] = useState(0);
    const [page, setPage] = useState(1);
    const [pageSize, setPageSize] = useState(10);
    const [selectedClub, setSelectedClub] = useState(null);
    const [isDeleteDialogOpen, setIsDeleteDialogOpen] = useState(false);
    const [isEditDialogOpen, setIsEditDialogOpen] = useState(false);

    useEffect(() => {
    }, [page, pageSize]);

    const handlePageChange = (newPage) => {
        setPage(newPage);
    };

    const handlePageSizeChange = (newPageSize) => {
        setPageSize(newPageSize);
    };

    const columns = [
        { field: 'title', headerName: 'Title' },
        { field: 'contactEmail', headerName: 'Contact Email' },
        { field: 'telephone', headerName: 'Telephone' },
        { field: 'location.title', headerName: 'Location Title' },
        { field: 'location.city', headerName: 'City' },
        {
            field: 'actions',
            headerName: 'Actions',
            sortable: false,
            renderCell: (rowData) => (
                <>
                    <IconButton color="primary" onClick={() => handleEditClub(rowData)}>
                        <Edit />
                    </IconButton>

                    <IconButton color="tertiary-secondary" onClick={() => handleDeleteClub(rowData)}>
                        <Delete />
                    </IconButton>
                </>
            ),
        },
    ];

    const sortableColumns = ['title', 'location.title', 'location.city'];

    const handleDeleteClub = (clubData) => {
        setSelectedClub(clubData);
        setIsDeleteDialogOpen(true);
        setIsEditDialogOpen(false);
    };

    const handleEditClub = (clubData) => {
        setSelectedClub(clubData.row);
        setIsEditDialogOpen(true);
        setIsDeleteDialogOpen(false);
    };

    const handleConfirmDelete = () => {
        if (selectedClub) {
            ClubService.deleteClub(selectedClub.id)
                .then(() => {
                    setClubList((prevClubs) => prevClubs.filter((club) => club.id !== selectedClub.id));
                    toast.success('Club successfully deleted!');
                })
                .catch((error) => {
                    toast.error('Failed to delete club!');
                });
        }

        setIsDeleteDialogOpen(false);
    };

    const handleConfirmUpdateClub = (updatedClub) => {
        if (updatedClub) {
            ClubService.updateClub(updatedClub)
                .then(() => {
                    setClubList((prevClubs) =>
                        prevClubs.map((club) => (club.id === updatedClub.id ? updatedClub : club))
                    );
                    toast.success('Club data successfully updated!');
                    setIsEditDialogOpen(false);
                })
                .catch((error) => {
                    toast.error('Failed to update club data!');
                });
        }
    };

    return (
        <div>
            <ServerPaginationDataTable
                data={clubList}
                columns={columns}
                sortableColumns={sortableColumns}
                totalCount={totalCount}
                page={page}
                onPageChange={handlePageChange}
                onPageSizeChange={handlePageSizeChange}
            />

            <ConfirmationDialog
                open={isDeleteDialogOpen}
                title="Delete Club"
                message="Are you sure you want to delete this club?"
                onConfirm={handleConfirmDelete}
                onCancel={() => setIsDeleteDialogOpen(false)}
            />

            {isEditDialogOpen && (
                <EditClubDialog
                    club={selectedClub}
                    onUpdateClub={handleConfirmUpdateClub}
                    onClose={() => setIsEditDialogOpen(false)}
                />
            )}
        </div>
    );
};

export default ClubsList;
