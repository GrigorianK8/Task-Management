import React from 'react'
import { Avatar } from '@mui/material'
import './Navbar.css'
import { useSelector } from 'react-redux';

const Navbar = () => {
	const { auth } = useSelector(store=>store);
	return (
		<div className='navbar-line w-full z-10 sticky left-0 right-0 top-0 py-3 px-5 lg:px-10 flex justify-between items-center'>
			<p className='font-bold text-lg'>Task Manager</p>

			<div className='flex items-center gap-5'>
				<p>{auth.user?.fullName}</p>
				<Avatar src='https://cdn.pixabay.com/photo/2017/11/10/05/48/user-2935527_1280.png'>
					C
				</Avatar>
			</div>
		</div>
	)
}

export default Navbar
