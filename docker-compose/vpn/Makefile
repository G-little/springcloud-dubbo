all:
	eval $(docker-machine env yourserver)
	docker-compose run --rm openvpn-server ovpn_genconfig -u udp://vpn.xiaogang.org.cn
	docker-compose run --rm openvpn-server ovpn_initpki
	docker-compose run --rm openvpn-server easyrsa build-client-full xiaogang nopass
	docker-compose run --rm openvpn-server ovpn_getclient xiaogang > xiaogang.ovpn
