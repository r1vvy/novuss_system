import React, { useEffect, useState } from 'react';
import ClubsList from "../../../features/clubs/ClubsList";
import ClubService from "../../../services/ClubService";
import AddClubDialog from "../../dialogs/club/AddClubDialog";
import { Button } from "@mui/material";
import {toast} from "react-toastify";

const ClubsListPage = () => {
    const [clubs, setClubs] = useState([]);
    const [page, setPage] = useState(0);
    const [pageSize, setPageSize] = useState(10);
    const [totalCount, setTotalCount] = useState(0);
    const [isAddClubDialogOpen, setIsAddClubDialogOpen] = useState(false);

    useEffect(() => {
        fetchClubs();
    }, [page, pageSize]);

    const fetchClubs = async () => {
        try {
            const response = await ClubService.getAllClubsByPage(page, pageSize);
            const data = response.content;

            const mappedData = data.map((club) => ({
                id: club.id,
                title: club.title,
                email: club.location.contactPerson.email,
                phoneNumber: club.location.contactPerson.phoneNumber,
                address: club.location.address,
                city: club.location.city,
            }));

            setClubs(mappedData);
            setTotalCount(response.totalElements);

            console.log(mappedData)
        } catch (error) {
            toast.error('Failed to load club data');
        }
    };


    const handlePageChange = (newPage) => {
        setPage(newPage);
    };

    const handlePageSizeChange = (newPageSize) => {
        setPageSize(newPageSize);
        setPage(1);
    };

    const handleAddClub = () => {
        setIsAddClubDialogOpen(true);
    };

    const handleCreateClub = (newClub) => {
        setIsAddClubDialogOpen(false);
    };

    return (
        <div>
            <h1 className="primary">Club Data</h1>

            <Button
                variant="contained"
                color="primary"
                onClick={handleAddClub}
                style={{ marginBottom: '10px' }}
            >
                Pievienot klubu
            </Button>

            <ClubsList
                clubs={clubs}
                totalCount={totalCount}
                page={page}
                pageSize={pageSize}
                onPageChange={handlePageChange}
                onPageSizeChange={handlePageSizeChange}
            />

            {isAddClubDialogOpen && (
                <AddClubDialog
                    isOpen={isAddClubDialogOpen}
                    onCreateClub={handleCreateClub}
                    onCancel={() => setIsAddClubDialogOpen(false)}
                />
            )}
        </div>
    );
};

export default ClubsListPage;
