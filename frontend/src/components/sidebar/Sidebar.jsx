import React, { useState } from 'react'
import { Avatar, Button } from '@mui/material'
import './Sidebar.css'
import CreateNewTaskForm from '../task/CreateTask'
import { useLocation, useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { logout } from '../../redux/AuthSlice';

const menu = [
	{ name: 'Home', value: 'Home', role: ['ROLE_ADMIN', 'ROLE_CUSTOMER'] },
	{ name: 'DONE', value: 'DONE', role: ['ROLE_ADMIN', 'ROLE_CUSTOMER'] },
	{ name: 'ASSIGNED', value: 'ASSIGNED', role: ['ROLE_ADMIN'] },
	{ name: 'NOT ASSIGNED', value: 'PENDING', role: ['ROLE_ADMIN'] },
	{ name: 'Create New Task', value: '', role: ['ROLE_ADMIN'] },
	{ name: 'Notification', value: 'NOTIFICATION', role: ['ROLE_CUSTOMER'] },
];

const role = 'ROLE_ADMIN'

const Sidebar = () => {
	const [activeMenu, setActiveMenu] = useState('DONE');
	const [openCreateTaskForm, setOpenCreateTaskForm] = useState(false);
	const dispatch = useDispatch();
	const location = useLocation();
	const navigate = useNavigate();

	const handleCloseCreateTaskForm = () => {
    setOpenCreateTaskForm(false);
  };
  
	const handleOpenCreateTaskForm = () => {
    setOpenCreateTaskForm(true);
  };
	
	const handleMenuChange = (item) => {
		const updatedParams = new URLSearchParams(location.search);

		if (item.name === 'Create New Task') {
			handleOpenCreateTaskForm();
		}
		else if (item.name==='Home') {
			updatedParams.delete('filter');
			const queryString = updatedParams.toString();
			const updatedPath = queryString?`${location.pathname}?${queryString}`
			:location.pathname;
			navigate(updatedPath);
		}
		else {
			updatedParams.set('filter', item.value);
			navigate(`${location.pathname}?${updatedParams.toString()}`)
		}
		setActiveMenu(item.name);
	};
	
	const handleLogout = () => {
		dispatch(logout())
		console.log('handle logout');
	};
	
	return (
		<>
		<div className='card min-h-[85vh] flex flex-col justify-center fixed w-[20vw]'>
			<div className='space-y-5 h-full'>
				<div className='flex justify-center'>
					<Avatar
						sx={{ width: '8rem', height: '8rem' }}
						className='border-2 border-[#c24dd0]'
						src='https://cdn.pixabay.com/photo/2021/08/04/13/06/software-developer-6521720_1280.jpg'
					/>
				</div>
				
				{
					menu.filter((item)=> item.role.includes(role))
					.map((item)=> <p onClick={() => handleMenuChange(item)}
					className={`py-3 px-5 rounded-full text-center cursor-pointer 
					${activeMenu===item.name?'activeMenuItem':'menuItem'}`}>
						{item.name}
					</p>)
				}
				<Button onClick={handleLogout}
				sx={{padding: '.7rem', borderRadius: '2rem'}} fullWidth
				className='logoutButton '>
					logout
				</Button>
			</div>
		</div>
		
		<CreateNewTaskForm open={openCreateTaskForm} handleClose={handleCloseCreateTaskForm}/>
		</>
	)
}

export default Sidebar
