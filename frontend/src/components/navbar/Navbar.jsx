import React from 'react'
import { Avatar } from '@mui/material'
import './Navbar.css'

const Navbar = () => {
	return (
		<div className='navbar-line w-full z-10 sticky left-0 right-0 top-0 py-3 px-5 lg:px-10 flex justify-between items-center'>
			<p className='font-bold text-lg'>Task Manager</p>

			<div className='flex items-center gap-5'>
				<p>grigoriank8</p>
				<Avatar src='https://i.pinimg.com/736x/9a/59/52/9a59520914ff8d4d7032d7c2d6b97ed0.jpg'>
					C
				</Avatar>
			</div>
		</div>
	)
}

export default Navbar
