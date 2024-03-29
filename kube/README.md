# 쿠버네티스 (kubernetes)
> it 환경의 진화 
- 2000년대 초반에 가상화 환경으로 전환 
- 호스트 운영체제에 가상화 계층을 두고 그 위에 여러개의 x86 게스트 운영체제를 올려 사용하는 방식 
  - 비지니스 변화에 민첩하게 대응하고 비용 절감이 이유 
  - 인프라를 확장할때 scale out 방식을 사용하고 메인 프레임은 scale up 방식으로 확장 
  - scale out 은 서버대수를 늘리는 방식이고 scale up 은 CPU나 메모리를 추가 
  - scale out 방식을 사용하게 되면 기존 서버에 영향을 주지 않으면서 간편하게 확장이 가능 
  - 2010년대에는 public cloud 가 등장 
    - 내부에 시스템을 구축할경우 (온 프레미스) 빠른 변화에 대응할려면 많은 시간이 필요 
    - 이미 구축된 외부의 서버를 빌려서 필요할 때만 사용하는 형태의 클라우드가 주목바등ㅁ 
  - public cloud 분류 
    - lass (Infrastructure as a service)
      - 인프라만 클라우드 벤터의 장비를 빌려서 사용 
    - PasS(Platform as a service)
      - 개발 환경까지 클라우드 벤더가 제공하고 유지 관리 
    - SaaS(Software as a Service) 
      - 서비스 영역까지 클라우드 벤더가 제공하고 유지 관리 
### 직접구축 
>네트워킹 , 스토리지 ,서버 , 가상화 ,운영체제 미들웨어 ,런타임 , 데이터 ,어플리케이션 
- laaS
  - 운영체제 , 미들웨어 ,런타임, 데이터, 어플리케이션 
- PaaS 
  - 데이터 , 애플리케이션 
- SaaS 

- 현재는 컨테이너 기반으로 변화


### 컨테이너 
> 데스크톱, 기존의 it환경 또는 클라우드 등 어디서나 어플리케이션 및 서비스를 실행하는데 필요한 모든 요소를 포함하는 소프트웨어 패키지 
- 운영체제를 논리적인 구획(컨테이너)으로 나누고 애플리케이션을 격리된 환경에서 실행할 수 있다. 
- 일반적인 가상화의 경우는 하이퍼바이저 (하나의 컴퓨터를 여러대로 분할해서 사용할 수 있도록 해주는 소프트웨어)위에 
가상 머신을 올려서 사용하는데 컨테이너 환경에서는 하이퍼바이버 대신에 도커같은 컨테이너 런타임 위에 컨테이너를 올려 사용 

### 일반적인 가상화 
- 하드웨어 <-> 호스트 OS<-> 가상화(하이퍼바이저) <-> 가상머신 <-> 운영체제 <-> Bin/Binary
### 컨테이너 환경 
- 하드웨어 <-> 호스트 OS <-> 컨테이너 런타임(도커) <-> 컨테이너 <-> Bin/Binary

- 일반적인 가상화 환경은 하드웨어 수준에서 가상화되지만 컨테이너는 운영체제 수준에서 가상화 
- 컨테이너는 운영체제의 커널을 공유하기 때문에 가볍고 유연하게 운영할 수 있다.
- 컨테이너가 가상화 환경에 비해서 리소스 소모가 적음 
- 운영체제를 공유하기 때문에 패치 및 업데이트 같은 유지 관리와 관련된 오버헤드가 줄어듬 

### 컨테이너를 생성하고 실행할 수 있도록 도와주는 것이 Container Runtime 

### Container Runtime 의 종류 
> 쿠버네티스에서는 2020년 부터 containerd 지원하겠다고 발표함 
- containerd 
  - 도커 사에서 개발한 오픈 소스 런타임 
  - 컨테이너 이미지를 내려받고 압축을 풀고 컨테이너를 실행하고 감독하는 컨테이너 전체 수명 주기를 관리하는 기능을 제공 
- docker containered
  - 위에 설치되는 데몬 
- CRI-O 
  - 레드햇 , 인텔 ,수세(Suse),Hyper,IBM 등의 관리자 및 커뮤니티를 중심으로 개발된 오픈소스 런타임

### 컨테이너 오케스트레이션 툴 
> 컨테이너를 효과적으로 관리하도록 도와주는 것을 컨테이너 오케스트레이션이라고 한다 
- 여러 시스템에 컨테이너를 분산해서 배치하거나 문제가 생긴 컨테이너를 교체하는 등 여러 역할을 수행 
  - 이러한 툴로 kubernetes(구글에서 개발한 오케스트레이션 툴로 기능이 가장 많고 가상화 및 퍼블릭 클라우드 등 다양한 환경에서 작동),docker swarm(도커가 설치된 여러대의 서버를 클러스터로 묶어 단일 환경으로 사용할 수 있는 툴 ), apache mesos 등이 있다 .

### 도커와 쿠버네티스의 관계 
>도커는 필수지만 쿠버네티스는 선택 
- 도커는 컨테이너를 실행하는 런타임 
- 쿠버네티스는 다주의 컨테이너를 관리하는 툴 

### 쿠버네티스 pod
> pod이란 서로 유기적인 애플리케이션이 실행중인 컨테이너의 집합 
- 쿠버네티스에서는 컨테이너를 pod 의 하위 요소로 다루게 된다 


### 쿠버네티스를 위한 개념 
> 쿠버네티스가 대중화 된 이유 
- DevOps 의 성숙화 
- Micro Service Architecture 로의 변화 
- DevOps
  - development와 operation을 결합해 탄생한 개발 방법론 
  - 시스템 개발자 와 운영자의 소통,협업,통합 및 자동화를 강조하는 소프트웨어 개발 방법론 또는 문좌 


### Monolithic Architecture 와 MicroService Architecture
- Monolithic Architecture 
  - 모든 모듈이 하나의 덩어리로 구성된 서비스 또는 애플리케이션 
- MicroService Architecture 
  - 모든 모듈들이 모두 분리되서 API 로 통신하는 서비스 또는 애플리케이션 

### CI/CD
- CI(Continous Intergration - 지속적 통합)
  - 거대한 애플리케이션은 개발자 한 명이 단독으로 개발하는 경우는 거의 없는데 다수의 개발자가 개별적인 모듈을 개발한 후 병합하는 과정을 거치게 되는데 이 때 병합시간이 오래걸리고 
  버그도 많이 발생한다 
  - 통합을 할 대 사람의 개입을 최소한으로 해서 통합을 해나가는 방식 
    - 가장많이 사용되는 툴이 jenkins
- CD(Continous Development - 지속적 배포 )
  - 애플리케이션에 변경 사항이 발생하면 이에 대한 오류가 있는지 테스트 한 후 프로그램을 repository에 업로드하고 마지막으로 운영 환경에 자동으로 배포하는것 
  ArgoCD같은 자동화 배포 툴을 이용한다 
- 여러개발자 -> jenkins -> github -> argo 를 이용해서 쿠버네티스에 애플리케이션을 배포 

### kubernetes 
> 컨테이너 기반의 애플리케이션을 개발하고 배포할 수 있도록 설계된 오픈 소스 플래폼 
- 하나의 애플리케이션을 생성하기 위해서는 pod이 필요함 
- pod가 쿠버네티스에서 생성할 수 있는 가장 작은 배포 단위 
- 파드 외에 service, volume, namespace 등을 묶어서 object라고 하는데 object는 쿠버네티스에서 상태 관리가 필요한 대상 
- 쿠버네티스는 기본적으ㅗ 마스터 노드 1개 와 워커 노드 1개로 구성 
- pod 
  - 쿠버네티스 의 가장 작은 배포 단위 
  - 컨테이너는 독립적으로 실행되는 애플리케이션의 개념에 가깝고, pod는 동시에 사용되어야 하는 컨테이너의 모임 
- service
  - 배포한 pod를 외부에서 접근할 수 있도록 해주는 것 
- namespace
  - 쿠버네티스 클러스터의 논리적인 분리 단위 
- volume
  - 컨테이너의 파일을 저장하고 컨테이너 간 파일을 공유할 수 있는 저장소 
- 워커노드
  - 도커 런타임이 설치된 환경에서 컨테이너 혹은 도커를 실행하고 유지 및 관리하는 것
- 마스터 노드는 전체 쿠버네티스 환경을 관리하고 워커노드는 컨테이너를 실행하고 관리 
- 워커 노드의 자원사용률이 높아지면 우커노드의 개수는 늘릴수있다 
- 워커 노드의 개수와 상관없이 마스터 노드 와 워커 노드로 구성된 쿠버네티스를 하나의 클러스터라고한다 

### 특징 
- 무중단서비스 
  - 서비스 중단없이 애플리케이션을 업그레이드 할 수 있어서 안정적으로 서비스를 제공 
  - 클라우드 밴더 종속성 해결 
    - Lock In 
      - 제품의 호환성 문제로 특정 클라우드 벤더에 의존하는현상 
- 유연한 확장성 
  - 파드를 늘리고 줄이는 것이 쉬움 
- 애플리케이션 개발의 단순화



### 기본개념 
> Architecture
- 쿠버네티스 클러스터 
  - 여러 리소스를 관리하기 위한 집합체로 마스터 노드 와 워커 노드로 하나의 클러스터를 구성
- 마스터 노드 
  - 쿠버네티스 클러스터 전체를 관리하는 시스템으로 ,control plane 이라고 한다 
- 워커 노드
  - 마스터 노드에 의해 명령을 받아서 파드를 생성하고 서비스한다고해서 컴퓨팅 머신이라고한다 
- 컨테이너 런타임
  - 파드를 실행하는 엔진
- 영구 스토리지 
  - 파드는 휘발성이라서 워커 노드에 떠 있는 파드가 삭제되면 파드 안의 모든 데이터도 즉시 삭제되기 때문에 데이터베이스 와 같은 중요한 데이터는 
  파드 외부에 있는 영구 스토리지에 저장해야한다 
  - CSI(Controller Storage Interface) 
    - 외부 스토리지를 파드에 연결할 수 있다 

### 쿠버네티스 컴포넌트 
> 쿠버네티스는 새로운 파드를 배포할 때 yaml 파일을 작성해서 실행하고 관리 
- 쿠버네티스에서는 이러한 yaml파일을 manifest라고 한다 
- API Server 
  - 쿠버네티스 클러스터의 API를 사용할 수 있게 해주는 프로세스로 클러스터 요청이 들어왔을때 그 요청이 유효한지 검증한다 
- 사용자 검증을 하고 사용자가 보낸 명ㄹ영어 (kubectl create -f development.yml) 를 전송하면 명령어가 문법에 맞게 작성되었는지
검증하고 사용자의 요청에 따라 파드를 생성하는데 API Server가 워커 노드에 파드를 생성하도록 요청은 했지만 아직 생성은 안됨 
- etcd
  - API Server는 파드를 만든 다는 사실을 etcd에 알리고 사용자에게 파드가 생성되었을 알립니다 
  - 클러스터의 상태를 저장하는 곳으로  (key- value)형태로 저장하고 있고 사용자에게 파드가 생성되었음을 알렸지만 내부적으로는 파드가 생성되지 않은 상태이다
- 스케쥴러 (kube-scheduler)
  - 파드를 위치시킬 적당한 워커 노드를 확인하고 이사실을 API 서버에 알린다 
  - 
### kubelet
> 파드가 생성될 워커 노드에 있는 kubelet에 파드의 생성 정보를 전달 
- kubelet이 해당 정보를 이용해서 파드를 생성 
- kubelet은 클러스터의 각 노드에서 실행되는 에이전트 
- 파드에서 컨테이너 동작을 관리 
- 파드가 생성되면 kubelet은 API Server 에 생성된 파드의 정보를 전달하고 API Server 는 다시 etcd를 업데이트한다 

### 마스터 노드 
- API Server 와 etcd로 구성

### 워커 노드 
- kubelet 과 파드로 구성 

### Controller Manager 
- kube-controller-manager 와 cloud-controller-manager 두 가지 유형 
- kube-controller-manager 
  - 다양한 컴포넌트의 상태를 지속적으로 모니터링하는 동시에 실행 상태를 유지하는 역할을 담당하는데 특정 워커 노드 와 통신이 불가능하다고 판단이 되면 해당 노드에 할당된 파드를 제거하고 다른 워커 노드에서 생성해 서비스가 계속되도록 합니다 .

### cloud-controller-manager
- EKS,AKS 와 같은 퍼블릭 클라우드에서 제공하는 쿠버네티스와 연동하는 서비스를 관리 


### proxy 
> 클러스터의 모든 노드에서 실행되는 네트워크의 프록시 
- 노드에 대한 네트워크 규칙을 관리하고 클러스터 내부 와 외부에 대한 통신을 담당함 

### 컨테이너 런타임 
> 컨테이너의 실행을 담당하는 것 



### 쿠버네티스 컨트롤러 
> 파드를 관리하는 역할을 수행하는 것으로 DaemonSet, Deployment, 래플리카셋 ,스테이트풀셋,잡크론잡,레플리케이션 컨트롤러 
- Deployment
  - 쿠버네티스에서 상태가 없는 애플리케이션을 배포할 때 사용되는 가장 기본적인 컨트롤러로 레플리카셋의 상위 개념이면서 파드를 배포할 때 사용 
  - 다양한 방법을 지원해서 배포할 때 세밀한 조작이 가능 
- ReplicaSet
  - 몇개의 파드를 유지할 지 결정하는 컨트롤러 
  - 5개의 파드를 유지하도록 설정했다면 한개의 파드가 삭제되더라도 5개의 파드를 유지하기위해서 다른 pod 한개를 생성함  
- Job
  - 하나 이상의 파드를 지정하고 지정된 수의 파드가 성공적으로 실행되도록 하는 것 
  - 노드의 하드웨어 장애나 재부팅 등으로 파드가 비정상적으로 작동하면 다른 노드에서 파드를 시작해 서비스가 지속되도록 한다 
- CronJob
  - Job 의 일종으로 특정 시간에 특정 파드를 실행시키는 것 과 같이 지정한 일정에 따라서 잡을 실행시킬때 사용하는데 주로 애플리케이션 프로그램이나 데이터베이스에서 중요한 데이터를 백업하는데사용 

### DaemonSet 
> 디플로이먼트처럼 파드를 생성하고 관리 
- 디플로이먼트가 실행해야 할 파드의 개수와 배포 전략을 세분화해서 조작할 수 있는 반면에 특정 노드 또는 모든 노드에 파드를 배포하고 관리하는데 데몬셋은 주로 노드마다 배치해야 하는 성능 수집 및 로그 수집같은 작업에 사용 
- taint 와 toleration 
  - 특정 워커 노드에는 특정 성격의 파드만 배포하고자 할 때 사용 
  - GPU 가 설치된 워커 노드에는 GPU가 필요한 서비스만 배포하고 싶은 경우 

### 서비스 
- 파드는 쿠버네티스 클러스터 안에서 옮겨 다니는 특성이 있는데 파드가 실행 중인 워커 노드에 문제가 생기면 다른 워커 노드에서 파드가 다시 생성될 수 있고 그 때마다 IP가 변경되는 특성이 있는데 이렇게 동적으로 변하는 파드에 고정된 방법으로 접근하기 위해서 사용되는 것이 service 
- service를 사용하면 파드가 클러스터 내의 어디에 떠 있든지 고정된 주소를 이용해서 접근할 수 있음 

### ClusterIP 
> 쿠버네티스 클러스터 내의 파드들은 기본적으로 외부에서 접근할 수 있는 IP를 할당받지 않지만 같은 클러스터 내부에서는 파드들이 통신할 방법을 제공하는데 이것이 클러스터 


### NodePort 
> 서비스를 외부로 노출할 때 사용하는 것으로 워커 노드의 IP 와 포트를 이용 


### LoadBalancer 
> 퍼블릭 클라우등에 존재하는 로드밸런서에 연결하고자 할 때 사용 
- 사용자는 물리적인 워커 노드의 위치와 상관없이 요청을 하게되면 로드밸런서 트래픽을 보고 적절한 워커 노드의 파드를 결정해서 사용하도록 해준다 

### 쿠버네티스의 통신 
> 특징 
- 파드가 사용하는 네트워크 과 호스트(노드) 가 사용하는 네트워크는 다름 
- 노드 내의 파드들은 가상의 네트워크를 이용하지만 호스트는 물리 네트워크를 이용 
- 같은 노드에 떠 있는 파드끼리만 통신이 가능 
- 다른 노드의 파드와 통신하려면 CNI 플로그 인이 필요 
  - 쿠버네티스 설치 할 때 자동으로 구성되지 않기때문에 별도로 설치해야한다 

### 같은 파드에 포함된 컨테이너 간 통신 
> 모두 동일한 Ip를 가지므로 포트번호를 이용해서 통신 

### 단일 노드에서 파드 간 통신 
> 같은 대역을 사용 함 
- IP 를 가지고 통신

### 다른 노드에서 파드간 통신 
> 오버레이 네트워크를 이용해서 물리적인 네트워크 위에 가상의 네트워크를 구성

### 파드 와 다른 서비스 간의 통신 
> 리눅스 커널에서 제공하는 netfilter(서비스 IP를 특정 IP로 변경할 수있는 규칙을 저장하고 변환하는 역할을 수행 ) 와 iptables를 이용 


### 외부 와 서비스간 통신 
- NodePort,LoadBalencer ,Ignress를 이용해서 통신 

### 쿠버네티스 설치 및 사용법 
> 쿠버네티스 종류 
- Cloud Native Computing Foundation(CNCF) 단체에서 제정한 표준 
  - 구글에서 개발되었지만 구글 등의 회사가 CNCF 재단을 만들고 쿠버네티스를 기부해서 오픈소스로 전환
- CNCF 도 쿠버네티스를 만들고 있지만 관리 기능을 강화한 버전이나 크기를 줄인 버전 등 쿠버네티스의 규격을 따른 3rd party 소프트웨어가 등장 
- AWS 나 Azure, GCP 와 같은 클라우드 서비스에서는 자사 서비스에 맞춰 커스터마이징된 쿠버네티스를 제공 
  - 이들 소프트웨어는 서로 호환이 되는데 이 경우 호환된 소프트웨어나 서비스에서는 Certified kubernetes 라는 인증을 구현 
  - https://www.cncf.io/certification/software-conformance 

### 원조 쿠버네티스 와 클라우드 버전 
> 원조 쿠버네티스를 채택하는 것은 흔한 경우이지만 이를 직접 구축하는 것은 별개의 문제 
- 외주를 주는 경우가 많음 
  - 직접 구축하는 경우 서비스 마다 부여되는 클러스터 IP 에 로드밸런싱을 적용할려면 이를 지원하는 하드웨어를 갖춰야 하고 드라이버에도 상성 문제가 있어서 하드웨어 선정부터 전문지식을 갖춰야함 
- 일반적으로는 AWS 와 같은 클라우드 컴퓨팅 서비스를 사용해 구축하는 경우가 많다 
- AWS에서는 EC2 나 Fargate를 워커 노드로 사용하고 EKS를 사용해서 관리하는데 여기서 EKS가 마스터노드이며 가상 서버로 구성되어 있어서 EKS 요금 이외에 가상 서버 요금 (EC2 나 Fargate) 이 서버 대수 만큼 부과된다 

### 도커데스크탑 과 미니쿠베 (Minikube)
- docker-desktop 은 쿠버네티스가 포함되어 있어서 etcd나 CNI를 설치하지 않고도 실습이 가능 
- 리눅스에서는 Minikube라는 간단히 사용할 수 있는 쿠버네티스가 제공됨 
- 쿠버네티스는 마스터 노드 와 워커 노드를 멸도의 물리적 컴퓨터로 설정하지만 도커 데스크탑이나 Minikube에서는 하나의 컴퓨ㅓ에 마스터 노드 와 워커 노드를 구축할 수 있다 
- 여기서 실습하는 정도는 명령어 나 설정 파일 (Menifest 파일) 을 작성해보는 정도이고 실제 시스템과는 차이가 난다.

### 도커 데스크탑에서 실습 환경 만들기 
- setting 탭으로 이동해서 kubernetes 에서 enable Kubernetes를 체크 

### 리눅스가 설치된 컴퓨터에 쿠버네티스 구축 
- 여러 대의 컴퓨터에 ubuntu 나 cent os 등의 리눅스 운영체제를 설치 
- 마스터 노드가 될 컴퓨터에 쿠버네티스와 CNI, etcd를 설치하는데 이 들은 kubeadm 이라는 도구를 이용해서 쉽게 설치 
- kubeadm init이라는 명령으로 클러스터를 초기화 한 후 워커 노드에서 kubeadm join 이라는 명령으로 
  - http://kubenetes.io/ko/docs/setup/production-environment/tools/kubeadm/install-kubeadm/

### 매니페스트 파일 작성 (쿠버네티스 정의 파일)
- 쿠버네티스는 패니페스트 파일에 기재된 내용에 따라 파드를 생성 
- 매니페스트 파일을 작성을 하고 쿠버네티스에 업로드하면 그 내용이 etcd에 바람직한 상태로 등록되고 서버 환경을 이 바람직한 상태로 유지 
- 파일을 작성하는 방법은 yaml 과 json 으로 가능 
  - yaml 이 인간이 읽기가 쉽기 때문에 yaml로 작성하는 것이 일반적 
    - 파일의 이름은 제한이 없지만 일반적으로 기억하기 쉽도록 작성함 
- 매니페스트 파일은 분할 작성이 가능 
  - 하나의 파일로 작성할 때는 각각의 리소스를 --로 구분하고 파일 단위로 나눌 때는 리소스를 구별할 수 있도록 이름을 붙입니다.

### 매니페이스트 작성할 내용
> 주항목
- apiVersion 
  - API 그룹 및 버전 
- kind 
  - 리소스 유형 
- metadata
  - 메타 데이터 
- spec
  - 리소스 내용 
- API 그룹 및 유형 
  - 파드 : core/v1
  - 서비스 : core/v1
  - 디플로이먼트 : apps/v1
  - 레플리카세트 : apps/v1

### 메타데이터 와 스펙 
- 메타 데이터에는 리소스의 이름이나 레이블을 기재 
- 스펙은 리소스의 내용을 정의 
- 주요 메타 데이터 
  - name
    - 리소스의 이름 , 문자열된 유일한 식별자 
  - namespace
    - 리소스를 세분화 한 DNS 호환 테이블
  - uid
    - 유일한 식별 
  - resourceVersion
    - 리소스 버전 
  - generation
    - 생성 순서 
  - createionTimestamp
    - 생성 일시 
  - deletionTimestamp
    - 삭제 일시 
  - labels
    - 임의의 레이블 
  - anotation
    - 리소스에 설정할 값 

### 실습 (정의 파일을 저장할 디렉토리를 생성)
- pod 을 생성하기 위한 yaml파일을 만든다 (kube/kube_folder/..yml)
```yaml
apiVersion: v1
kind: Pod
metadata:
  name: apa000pod
  labels:
    app: apa000kube
spec:
  containers:
    - name: apa000ex91
      image: httpd
      ports:
        - containerPort: 80

```

- 디플로이먼트의 메타 데이터 와 스펙 작성 
```yml
apiVersion:
kind:
metadata:
  name: <- 디플로이먼트 이름 
spec:
  selector:       <- 실렉터 설정 
    matchLabels: <- 실렉터가 선택할 관리 대상 레이블 
  replicas:      <- 레플리카 설정 
  template:       <- 템플릿 (파드의 정보)
    metadata:     <- 파드의 메타데이터를 기재 
    spce:         <- 파드의 스펙을 기재 
```

### Deployment를 생성하기 위한 apa000deploy.yml 파일작성 
```yml
apiVersion: apps/v1
kind: Deployment
metadata:
  name:
spec:
  selector:
    matchLabels:
      app: app000kube
  replicas: 3
  template:
    metadata:
      labels:
        aap: app000kube
    spec:
      containers:
        - name: app000ex91
          image: httpd
          ports:
          - containerPort: 80
```
- pod는 쿠버네티스 명령어로 실행시킬수없다 

### 쿠버네티스 명령어 
> kubectl 커맨드 옵션 
- 커맨드 
  - create
    - 리소스 생성
  - delete
    - 리소스를 삭제 
  - get
    - 리소스 상태 출력 
  - set
    - 리소스 값을 출력 
  - apply
    - 리소스 변경사항을 반영 
  - scale
    - 레플리카 수를 변경 
  - logs
    - 컨테이너의 로그를 화면에 출력 

### 실행 
```
kubectl apply -f /Users/kimjeounghoun/Jeounghoun/강의/kakaoCloudSchool/kube/kube_folder/apa000deploy.yml
```