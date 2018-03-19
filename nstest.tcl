set ns [new Simulator]

set nf [open out1.nam w]
$ns namtrace-all $nf

proc finish {} {
	global $ns $nf
	$ns flush-trace

	close $nf
	
	exec nam out1.nam &

	exit
}

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]

$ns duplex-link $n0 $n2 1Mb 20ms DropTail
$ns duplex-link $n1 $n2 1Mb 20ms DropTail
$ns duolex-link $n2 $n3 1.5Mb 30ms DropTail

$ns queue-limit $n2 $n3 10

set tcp [new Agent/TCP]
$tcp set class_ 2
$ns attach-agent $n0 $tcp
set sink [new TCPSink]
$ns attach-agent $n3 $sink
$ns connect $tcp $sink
$tcp set fid_ 1

set ftp [new Application/FTP]
$ftp attach-agent $tcp
$ftp set type_ FTP

$ns at 1 "$ftp start"
$ns at 4 "$ftp stop"

$ns at 5 "finish"




$ns run