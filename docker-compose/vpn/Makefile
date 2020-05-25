all:
	eval $(docker-machine env yourserver)
	docker-compose run --rm openvpn-server ovpn_genconfig -u udp://192.168.0.195
	docker-compose run --rm openvpn-server ovpn_initpki
	docker-compose run --rm openvpn-server easyrsa build-client-full 192.168.0.195 nopass
	docker-compose run --rm openvpn-server ovpn_getclient mydomain.lt > mydomain.lt.ovpn
