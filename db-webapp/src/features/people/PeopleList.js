import React, { useEffect, useState } from 'react';
import { IconButton } from '@mui/material';
import { Delete, Edit } from '@mui/icons-material';
import ServerPaginationDataTable from '../../components/ServerPaginationDataTable';
import ConfirmationDialog from '../../components/dialogs/ConfirmationDialog';
import EditPersonDialog from "../../components/dialogs/person/EditPersonDialog";
import { toast } from 'react-toastify';
import PeopleService from '../../services/PersonService';

const PeopleList = () => {
    const [people, setPeople] = useState([]);
    const [selectedPerson, setSelectedPerson] = useState(null);
    const [isDeleteDialogOpen, setIsDeleteDialogOpen] = useState(false);
    const [isEditDialogOpen, setIsEditDialogOpen] = useState(false);
    const [totalCount, setTotalCount] = useState(0);
    const [page, setPage] = useState(0);
    const [pageSize, setPageSize] = useState(100);

    useEffect(() => {
        fetchPeople(0, pageSize);
    }, []);

    const formatDate = (dateString) => {
        const date = new Date(dateString);
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        return `${year}-${month}-${day}`;
    };

    const fetchPeople = (page, pageSize) => {
        PeopleService.getAllPeople(page, pageSize)
            .then((response) => {
                const { content, pageable, totalElements } = response;

                const mappedData = content.map((person) => ({
                    id: person.id,
                    firstName: person.firstName,
                    lastName: person.lastName,
                    email: person.email,
                    phoneNumber: person.phoneNumber,
                    birthDay: formatDate(person.birthDay), // Format the date here
                }));

                setPeople(mappedData);
                setTotalCount(totalElements);
                setPage(page);
                setPageSize(pageSize);
            })
            .catch((error) => {
                console.log(error);
                toast.error('Failed to fetch people!');
            });
    };


    const handlePageChange = (newPage) => {
        fetchPeople(newPage, pageSize);
    };
    const handlePageSizeChange = (newPageSize) => {
        setPageSize(newPageSize);
        fetchPeople(page, newPageSize);
    };

    const handleDeletePerson = (rowData) => {
        setSelectedPerson(rowData);
        setIsDeleteDialogOpen(true);
        setIsEditDialogOpen(false); // Close the edit dialog if it's open
    };

    const handleEditPerson = (rowData) => {
        setSelectedPerson(rowData);
        setIsEditDialogOpen(true);
        setIsDeleteDialogOpen(false); // Close the delete dialog if it's open
    };

    const handleConfirmDelete = () => {
        if (selectedPerson) {
            PeopleService.deletePerson(selectedPerson.id)
                .then(() => {
                    setPeople((prevPeople) => prevPeople.filter((person) => person.id !== selectedPerson.id));
                    toast.success('Persona veiksmīgi izdzēsta!');
                })
                .catch((error) => {
                    toast.error('Neizdevās izdzēst personu!');
                });
        }

        setIsDeleteDialogOpen(false);
    };

    const handleConfirmUpdatePerson = (updatedPerson) => {
        if (updatedPerson) {
            console.log(updatedPerson);
            PeopleService.updatePerson(updatedPerson)
                .then(() => {
                    setPeople((prevPeople) =>
                        prevPeople.map((person) => (person.id === updatedPerson.id ? updatedPerson : person))
                    );
                    toast.success('Personas dati veiksmīgi atjaunināti!');
                    setIsEditDialogOpen(false);
                })
                .catch((error) => {
                    toast.error('Neizdevās atjaunināt personas datus!');
                });
        }
    };



    const columns = [
        { field: 'id', headerName: 'ID' },
        { field: 'firstName', headerName: 'Vārds' },
        { field: 'lastName', headerName: 'Uzvārds' },
        { field: 'email', headerName: 'E-pasts' },
        { field: 'phoneNumber', headerName: 'Telefona nr.' },
        { field: 'birthDay', headerName: 'Dzimšanas diena' },
        {
            field: 'actions',
            headerName: 'Actions',
            sortable: false,
            renderCell: ({ row }) => (
                <>
                    <IconButton color="primary" onClick={() => handleEditPerson(row)}>
                        <Edit />
                    </IconButton>

                    <IconButton color="tertiary-secondary" onClick={() => handleDeletePerson(row)}>
                        <Delete />
                    </IconButton>
                </>
            ),
        },
    ];

    const sortableColumns = ['firstName', 'lastName', 'email', 'phoneNumber', 'birthday']; // Specify the sortable columns

    return (
        <div>
            <ServerPaginationDataTable
                data={people}
                columns={columns}
                sortableColumns={sortableColumns}
                totalCount={totalCount}
                page={page}
                pageSize={pageSize}
                onPageChange={handlePageChange}
                onPageSizeChange={handlePageSizeChange}
            />

            <ConfirmationDialog
                isOpen={isDeleteDialogOpen}
                message="Vai esat pārliecināts, ka vēlaties dzēst šo personu?"
                onConfirm={handleConfirmDelete}
                onCancel={() => setIsDeleteDialogOpen(false)}
            />

            {selectedPerson && (
                <EditPersonDialog
                    isOpen={isEditDialogOpen}
                    person={selectedPerson}
                    onSave={handleConfirmUpdatePerson}
                    onCancel={() => setIsEditDialogOpen(false)}
                />
            )}
        </div>
    );
};

export default PeopleList;
