import { ThemeProvider } from '@mui/material'
import { darkTheme } from './theme/darktheme'
import Navbar from './components/navbar/Navbar'
import Home from './components/home/Home'
import Auth from './components/auth/Auth'
import { useDispatch, useSelector } from 'react-redux'
import { useEffect } from 'react'
import { fetchTasks } from './redux/TasksSlice'
import { getUserProfile } from './redux/AuthSlice'

function App() {
	const user = true;
	const dispatch = useDispatch();
	const {task, auth} = useSelector(store=>store);

	useEffect(() => {
		dispatch(fetchTasks({}))
		dispatch(getUserProfile(auth.jwt || localStorage.getItem('jwt')))
	}, [auth.jwt]);

console.log('task', task);
	
	return (
		<ThemeProvider theme={darkTheme}>
			{auth.user?<div>
			  <Navbar />
			  <Home />
			</div>:<Auth />}
		</ThemeProvider>
	)
}

export default App
