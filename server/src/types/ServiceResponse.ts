import http from 'http';

interface ServiceResponsePayload {
	message: string,
	data?: unknown
}

interface IServiceResponse {
	success: boolean,
	statusCode: number,
	payload: ServiceResponsePayload
}

class ServiceResponse implements IServiceResponse {
	success: boolean = false;
	statusCode: number = -1;
	payload: ServiceResponsePayload = { message: "miku" }

	get() {
		if (this.statusCode === -1) {
			throw new Error("Status code is still in default value you dumbfuck");
		}

		if (this.payload.message === "miku") {
			throw new Error("Payload message is still in default value you dumbfuck");
		}

		const response: IServiceResponse = {
			success: this.success,
			statusCode: this.statusCode,
			payload: {
				message: `${this.payload.message} (${http.STATUS_CODES[this.statusCode]})`,
				data: this.payload.data
			}
		};
		return response; 
	}
}

export default ServiceResponse;