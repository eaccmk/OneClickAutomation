#!/usr/bin/env bash

	#######**********************************#######
	#######***** Windows 32 BIT *************#######
	#######***** Java and Appium ************#######
	#######******Cucumber with Reporter *****#######
	#######**********************************#######

	#######
	## Counting Connected ios_devices
	#######
count_connected_ios_devices(){
    echo ""
	echo "Counting Connected ios_devices"
	i=0
	for line in $(system_profiler SPUSBDataType | sed -n -e '/iPhone/,/Serial/p' | grep "Serial Number:" | awk -F ":" '{print $2}'); do
		UDID=${line}
		iPhone_UDID_array[i]=${line}
		i=$(($i+1))
	done

	iPhone_count=${#iPhone_UDID_array[@]}
	echo ""
	echo "Total number of connected iPhone's  ===> " $iPhone_count
}


	#######
	## Counting Connected ios_simulators
	#######
count_connected_ios_simulators(){
    echo ""
	echo "Counting Connected ios_simulators"
	sim_i=0
	for sim_line in $(xcrun simctl list | egrep '([-0-9A-F]+\)' | tr -d '\(\)');do
		sim_UDID=${sim_line}
		ios_sim_UDID_array[sim_i]=${sim_line}
		sim_i=$(($sim_i+1))
	done

	ios_simulator_count=${#ios_sim_UDID_array[@]}
	echo ""
	echo "Total number of connected ios SIMULATORS ===> " $ios_simulator_count
}


	#######
	## Counting Connected android devices
	#######
    count_connected_android_devices(){
    echo ""
	echo "Counting Connected android devices"
	 android_UDID_array=()
	 i=0
	 while [[ $i -lt 2 ]]; do
        adb_full_path=`which adb`
#        echo `$adb_full_path devices`
		android_device_running=`$adb_full_path devices | grep "daemon not running"`
		if [[ $? == 0 ]]; then
			echo "[INFO] adb not ready. Waiting..." && sleep 5
			let i=i+1
		else
		    android_device_list=`$adb_full_path devices | grep -v "List of devices attached"`
			for device in $android_device_list; do
				if [[ $device != 'unauthorized' && $device != 'device' ]]; then
					echo ""
					echo "[INFO] Device's UDID is: ${device}"
					android_UDID_array+=($device)
				fi
    		done
			break
		fi
	done

	android_device_count=${#android_UDID_array[@]}
	echo ""
	echo "Total number of connected ANDROID devices ===> " $android_device_count

}

	#######
	## Counting Connected android_emulators
	#######
count_connected_android_emulators(){
	echo ""
	echo "Counting Connected android_emulators"
	# // todo

}

count_ALL_DEVICES(){
	# Calling specific methods (one by one) to Count Device ID's
	'count_connected_ios_devices'
	'count_connected_ios_simulators'
	'count_connected_android_devices'
	'count_connected_android_emulators'

	iOS_ALL=("${iPhone_UDID_array[@]}" "${ios_sim_UDID_array[@]}")
	# android_ALL=("${android_UDID_array[@]}" "${android_emulator_UDID_array[@]}")
	_All_Test_Device_UDID=("${iPhone_UDID_array[@]}" "${ios_sim_UDID_array[@]}" "${android_UDID_array[@]}")
	# _Total_Test_Device_Count=("${iPhone_UDID_array[@]}" "${ios_sim_UDID_array[@]}" "${android_UDID_array[@]}" "${}")
	echo ""
	echo "FINALLY Total number of Connected Devices are: ${#_All_Test_Device_UDID[@]}"
}

	#######
	## START unique Appium Servers and WDA ports for all connected devices
	#######
start_appium_servers(){

#	_count=${#_All_Test_Device_UDID[@]}
	_count=1

	echo ""
	echo "Starting ${_count} appium server(s)"
	#Initializing appium and WDA port's
	_appium_port=4724
	_wda_port=8100


	for((i=0;i<_count;i++));do
		_appium_port_array[i]=${_appium_port}
		_appium_port=$(($_appium_port+1))

		_wda_port_array[i]=${_wda_port}
		_wda_port=$(($_wda_port+1))
	done

	for((i=0;i<_count;i++));do
		echo ""
		echo "Starting Appium Server no ${i+1} on Appium Port" ${_appium_port_array[i]} " and WDA Port" ${_wda_port_array[i]} " for Device with ID " ${_All_Test_Device_UDID[i]}
		# Start Unique Instances of Appium and Run Them in background
		appium --port ${_appium_port_array[i]} --webdriveragent-port ${_wda_port_array[i]} &
	done

}

execute_tests(){
    echo ""
	echo "Starting Test Run/Executions"
	for((i=0;i<_count;i++));do
	echo "Starting Test Run/Executions"
	mvn verify -D_udid=${_All_Test_Device_UDID[i]} -D_port=${_appium_port_array[i]} -D_wda=${_wda_port_array[i]} &
#		mvn verify -Dcucumber.Options="--tags @$1" -D_udid=${_All_Test_Device_UDID[i]} -D_port=${_appium_port_array[i]} -D_wda=${_wda_port_array[i]}
	done


}


stop_appium_servers(){
		echo ""
		echo "Calling stop_appium_servers ... "
#		ps -f | grep appium | grep -v grep | awk '{print $2}' | xargs kill -9 -f-w
		tasklist | grep node | grep -v grep | awk '{print $2}' | xargs kill -9 -f-w
#		ps -f | grep appium | grep -v grep | awk '{print $2}' | xargs kill -9
		echo ""
		echo "All appium servers are stopped now !! "

}

kill_all_maven_java_processes(){
	os=`check_which_OS`
	if ["$(os)" == "Darwin"]
	then
		 echo ""
		 echo "Killing in Windows Way"
		 pkill -9 -f java
	elif ["$(os)" == "Windows"]
	then
 		echo ""
 		echo "Killing in Mac Way"
 		ps -f | grep java | grep -v grep | awk '{print $2}' | xargs kill -9
 	fi
}

kill_adb_devices(){
	# https://developer.android.com/studio/run/oem-usb.html#InstallingDriver

	adb kill-server
}


end_execution_gracefully(){
	echo ""
	echo "Ending Test Run"

	'stop_appium_servers'
	'kill_all_maven_java_processes'
 	'kill_adb_devices'
}

check_which_OS(){
if ["$(uname)" == "Darwin"]
then
	 os='Darwin'
 echo ""
 echo "Do something under Mac OS X platform"
elif ["$(expr substr $(uname -s) 1 5)" == "Linux"]
then
	os='Linux'
  echo ""
  echo "Do something under Linux platform"
elif [-n "$COMSPEC" -a -x "$COMSPEC"]
then
	os='Windows'
  echo ""
  echo "$0: this script does not support Windows \:\( "
fi
}

main(){
echo ""
echo ""
echo "========================"
echo "     Author : eaccmk    "
echo "========================"
echo ""
echo ""
	stop_appium_servers
	count_ALL_DEVICES
	    # count_connected_ios_devices
	    # count_connected_ios_simulators
#	     count_connected_android_devices
	    # count_connected_android_emulators
	start_appium_servers
	execute_tests
#	end_execution_gracefully
}

#initialize Script from this call
main "@"