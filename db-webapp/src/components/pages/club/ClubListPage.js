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
            const { data, totalCount: total } = response;
            setClubs(data);
            setTotalCount(total);
        } catch (error) {
            toast.error("Neizdevās ielādēt klubu datus")
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
        // Logic to create a new club
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
                Add Club
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
